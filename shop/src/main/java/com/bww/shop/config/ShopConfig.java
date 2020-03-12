package com.bww.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置类
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
public class ShopConfig {

    @Value("${user.appid}")
    private String appid;

    @Value("${user.user_nm}")
    private String userNm;

    @Value("${user.pass_wd}")
    private String passWd;


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

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
