package com.bww.shop.domain;


import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -6932036783548410064L;
    private Integer id;
    private String openid;
    private String name;
    private String headImg;
    private Integer sex;
    private String city;
    private String phone;
    private String addressInfo;
    private String userNm;
    private String passWd;
    private String salt;
    private String type;
    private java.util.Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", name='" + name + '\'' +
                ", headImg='" + headImg + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", addressInfo='" + addressInfo + '\'' +
                ", userNm='" + userNm + '\'' +
                ", passWd='" + passWd + '\'' +
                ", salt='" + salt + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                '}';
    }
}
