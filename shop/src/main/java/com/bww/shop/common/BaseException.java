package com.bww.shop.common;

public class BaseException extends RuntimeException{

    private Integer errCode;
    private String errMsg;


    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public BaseException(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BaseException(ResultCode resultCode) {
        this.errCode = resultCode.code();
        this.errMsg = resultCode.message();
    }

}
