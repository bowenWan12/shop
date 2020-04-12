package com.bww.shop.utils;

import com.bww.shop.common.BaseException;
import com.bww.shop.common.ResultCode;
import com.bww.shop.domain.User;

public class ValidataUtils {
    public static void validateUser(User user){

        if (user.getName()==null||user.getName()==""
                ||user.getHeadImg()==null||user.getHeadImg()==""){
            throw new BaseException(ResultCode.PARAM_NOT_COMPLETE);
        }
    }

}
