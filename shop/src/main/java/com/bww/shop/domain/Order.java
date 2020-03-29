package com.bww.shop.domain;


import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {


    private static final long serialVersionUID = 1418946854986274367L;
    private Integer id;
  private String openid;
  private String nickName;
  private String headImg;
  private String orderId;
  private String flowId;
  private String transDate;
  private String goodsMark;
  private String goodsInfo;
  private String state;
  private BigDecimal totalFee;
  private String del;
  private java.util.Date updateTime;
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


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public String getFlowId() {
    return flowId;
  }

  public void setFlowId(String flowId) {
    this.flowId = flowId;
  }


  public String getTransDate() {
    return transDate;
  }

  public void setTransDate(String transDate) {
    this.transDate = transDate;
  }


  public String getGoodsInfo() {
    return goodsInfo;
  }

  public void setGoodsInfo(String goodsInfo) {
    this.goodsInfo = goodsInfo;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public BigDecimal getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(BigDecimal totalFee) {
    this.totalFee = totalFee;
  }


  public String getDel() {
    return del;
  }

  public void setDel(String del) {
    this.del = del;
  }


  public java.util.Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.util.Date updateTime) {
    this.updateTime = updateTime;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

  public String getGoodsMark() {
    return goodsMark;
  }

  public void setGoodsMark(String goodsMark) {
    this.goodsMark = goodsMark;
  }
}
