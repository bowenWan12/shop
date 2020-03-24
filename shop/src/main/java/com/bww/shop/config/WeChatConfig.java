package com.bww.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class WeChatConfig {

    /**
     * 微信小程序appid
     */
    @Value("${applet.appid}")
    private String appletAppid;
    /**
     * 微信小程序appsecret
     */
    @Value("${applet.appsecret}")
    private String appletAppSecret;

    @Value("${applet.authCode2SessionUri}")
    private String authCode2SessionUri;

    public String getAppletAppid() {
        return appletAppid;
    }

    public void setAppletAppid(String appletAppid) {
        this.appletAppid = appletAppid;
    }

    public String getAppletAppSecret() {
        return appletAppSecret;
    }

    public void setAppletAppSecret(String appletAppSecret) {
        this.appletAppSecret = appletAppSecret;
    }

    public String getAuthCode2SessionUri() {
        return authCode2SessionUri;
    }

    public void setAuthCode2SessionUri(String authCode2SessionUri) {
        this.authCode2SessionUri = authCode2SessionUri;
    }
}
