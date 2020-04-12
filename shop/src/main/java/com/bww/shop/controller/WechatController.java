package com.bww.shop.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.bww.shop.common.ResultCode;
import com.bww.shop.common.constant.Base;
import com.bww.shop.common.constant.UserEnums;
import com.bww.shop.config.WeChatConfig;
import com.bww.shop.domain.Result;
import com.bww.shop.domain.User;
import com.bww.shop.service.UserService;
import com.bww.shop.utils.HttpUtils;
import com.bww.shop.utils.JWTUtils;
import com.bww.shop.utils.ValidataUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataloger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Result authCode2Session(@RequestBody User user) {
        //对传入对user进行判断
        if (user.getSalt() == null || user.getSalt() == "") {
            return Result.error(ResultCode.PARAM_NOT_COMPLETE, "code 不能为空");
        }

        //获取access_token、openid
        String uri =  "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                weChatConfig.getAppletAppid() +
                "&secret=" +
                weChatConfig.getAppletAppSecret() +
                "&js_code=" +
                user.getSalt() +
                "&grant_type=authorization_code";

        Map<String, Object> wxReturnMap = HttpUtils.doGet(uri);

        if (wxReturnMap == null || wxReturnMap.isEmpty()) {
            return Result.error(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, "微信调用失败");
        }

        String openId = (String) wxReturnMap.get("openid");
        if (openId == null || openId == "") {
            return Result.error(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, "未获取到openid");
        }
//        User dbEntity = userService.findByOpenId(openId);
        User dbEntity;

        //TODO
        try {
            logger.info("开始查找用户信息:----->>>" + openId);
            dbEntity = userService.findByOpenId(openId);
            logger.info("信息查找结束"+dbEntity.toString());
        } catch (Exception e) {
            return Result.error(ResultCode.ERROR, e.getMessage());
        }
        if (StringUtils.isBlank(dbEntity.getOpenid())) {
            logger.info("==============================="+user.toString());
            dbEntity.setOpenid(openId);
            dbEntity.setName(user.getName());
            dbEntity.setHeadImg(user.getHeadImg());
            dbEntity.setSex(user.getSex());
            dbEntity.setCity(StringUtils.trimToEmpty(user.getCity()));
            dbEntity.setPhone(StringUtils.trimToEmpty(user.getPhone()));
            dbEntity.setAddressInfo(StringUtils.trimToEmpty(user.getAddressInfo()));
            dbEntity.setUserNm("");
            dbEntity.setPassWd("");
            dbEntity.setSalt(user.getSalt());
            dbEntity.setType(UserEnums.UserType._0.geCode());
            logger.info(dbEntity.toString());
            ValidataUtils.validateUser(dbEntity);
            userService.saveWechatUser(dbEntity);
        }

        String token = JWTUtils.geneJsonWebToken(dbEntity);
        Result rs = new Result();

        rs.simple().put(Base.TOKEN, token);
        rs.setResultCode(ResultCode.SUCCESS);
        return rs;
    }

}
