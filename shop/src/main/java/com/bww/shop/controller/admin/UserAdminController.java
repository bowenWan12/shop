package com.bww.shop.controller.admin;

import com.bww.shop.common.ResultCode;
import com.bww.shop.common.constant.Base;
import com.bww.shop.common.utils.MD5Util;
import com.bww.shop.domain.Result;
import com.bww.shop.domain.User;
import com.bww.shop.dto.AdminUser;
import com.bww.shop.service.UserService;
import com.bww.shop.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/api/v1/web")
public class UserAdminController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataloger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Result authCode2Session(@RequestBody AdminUser adminUser) {
        Result rs = new Result();
        if (StringUtils.isBlank(adminUser.getUserNm())||StringUtils.isBlank(adminUser.getPassWd())) {
            rs.setResultCode(ResultCode.PARAM_NOT_COMPLETE);
            return rs;
        }

        //查找用户信息
        User user = userService.findByUserNm(adminUser.getUserNm());
        if (user == null || StringUtils.isBlank(user.getSalt())) {
            return Result.error(ResultCode.RESULE_DATA_NONE, "用户信息未找到");
        }

        String newPassWd = MD5Util.inputPassToDBPass(adminUser.getPassWd(), user.getSalt());
        if (newPassWd.equals(user.getPassWd())){
            String token = JWTUtils.geneJsonWebToken(user);
            rs.simple().put(Base.TOKEN, token);
            rs.setResultCode(ResultCode.SUCCESS);
            dataloger.info("管理员用户登录"+"_"+user.getUserNm()+"_"+adminUser.getUserNm());
            return rs;
        } else {
            rs.setResultCode(ResultCode.USER_LOGIN_ERROR);
            return rs;
        }
//        userService.updateUserAddressInfoByOpenid(addressInfo, user.getOpenid());

    }
}
