package com.bww.shop.domain;


import java.math.BigDecimal;

public class Flow {

  private Integer id;
  private String openid;
  private String orderId;
  private String flowId;
  private String transDate;
  private String goodsMark;
  private String goodsName;
  private BigDecimal amountFee;
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


  public String getGoodsMark() {
    return goodsMark;
  }

  public void setGoodsMark(String goodsMark) {
    this.goodsMark = goodsMark;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public BigDecimal getAmountFee() {
    return amountFee;
  }

  public void setAmountFee(BigDecimal amountFee) {
    this.amountFee = amountFee;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
