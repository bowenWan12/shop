package com.bww.shop.dto;

import java.io.Serializable;

public class AdminUser implements Serializable {

    private static final long serialVersionUID = 6637207389110057069L;
    private String userNm;
    private String passWd;

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }
}
