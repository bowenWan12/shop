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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
//@RequestMapping("/user/api/v1/user")
@RequestMapping("/api/v1/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataloger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private UserService userService;

    @PostMapping("/update_addressInfo")
    public Result authCode2Session(@RequestParam(value = "addressInfo", required = true) String addressInfo,
                                   HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        if (userId == null) {
            return Result.error(ResultCode.RESULE_DATA_NONE, "userId is null");
        }

        //查找用户信息
        User user = userService.findById(userId);
        if (user == null) {
            return Result.error(ResultCode.RESULE_DATA_NONE, "用户信息未找到");
        }
        userService.updateUserAddressInfoByOpenid(addressInfo, user.getOpenid());
        return Result.success();
    }

}
