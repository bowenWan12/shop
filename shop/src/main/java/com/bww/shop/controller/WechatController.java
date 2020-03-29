package com.bww.shop.controller;

import com.bww.shop.common.ResultCode;
import com.bww.shop.config.WeChatConfig;
import com.bww.shop.domain.Result;
import com.bww.shop.domain.User;
import com.bww.shop.service.UserService;
import com.bww.shop.utils.HttpUtils;
import com.bww.shop.utils.JWTUtils;
import com.bww.shop.utils.ValidataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    @ResponseBody
    public Result authCode2Session(User user) {

        //对传入对user进行判断
        if (user.getSalt() == null || user.getSalt() == "") {
            return Result.error(ResultCode.PARAM_NOT_COMPLETE);
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
            return null;
        }

        String openId = (String) wxReturnMap.get("openid");
        if (openId == null || openId == "") {
            return Result.error(ResultCode.PERMISSION_NO_ACCESS);
        }
//        String sessionKey = (String) stringObjectMap.get("session_key");

        User dbEntity = userService.findByOpenId(openId);
        if (dbEntity == null) {
            user.setOpenid(openId);
            ValidataUtils.validateUser(user);
            userService.saveWechatUser(user);
        }
        String token = JWTUtils.geneJsonWebToken(user);
        return Result.success(token);
    }

}
