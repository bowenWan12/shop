package com.bww.shop.controller;

import com.bww.shop.config.WeChatConfig;
import com.bww.shop.domain.JsonData;
import com.bww.shop.domain.User;
import com.bww.shop.service.UserService;
import com.bww.shop.utils.HttpUtils;
import com.bww.shop.utils.JWTUtils;
import com.oracle.tools.packager.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public JsonData authCode2Session(User user) {


        if (user.getSalt() == null || user.getSalt() == "") {
            return JsonData.buildError("code is null", 99);
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
        System.out.println(wxReturnMap);
        //TODO 判断微信服务器端返回
        String openId = (String) wxReturnMap.get("openid");
        if (openId == null || openId == "") {
            return JsonData.buildError("认证失败，请联系管理员["+wxReturnMap.get("errcode")+"]",99);
        }
//        String sessionKey = (String) stringObjectMap.get("session_key");

        //TODO db查询-更新或保存
        User dbEntity = userService.findByOpenId(openId);
        if (dbEntity == null) {
            user.setOpenid(openId);
            //TODO 各类属性值判断
            userService.saveWechatUser(user);
        }


        String token = JWTUtils.geneJsonWebToken(user);
        System.out.println("-------"+token);
        logger.info(user.toString());
        return JsonData.buildSuccess(token);
    }

}
