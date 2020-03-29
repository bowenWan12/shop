package com.bww.shop.common;

import com.bww.shop.domain.Result;
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
    public Result Hander(Exception e) {
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return Result.error(ResultCode.ERROR, baseException.getErrMsg());
        } else {
            return Result.error(ResultCode.ERROR);
        }

    }
}
