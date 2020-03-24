package com.bww.shop.common;

import com.bww.shop.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理控制器
 */
@ControllerAdvice
public class ExceptionHander {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Hander(Exception e) {


        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return JsonData.buildError(baseException.getErrMsg(),baseException.getErrCode());
        } else {
            return JsonData.buildError("全局异常，未知错误");
        }

    }
}
