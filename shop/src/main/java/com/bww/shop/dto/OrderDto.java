package com.bww.shop.dto;

import com.bww.shop.domain.GoodsOrder;

import java.util.List;

/**
 * 订单数据层对象
 */
public class OrderDto extends GoodsOrder {

    private List<String> goodsMarks;

    public List<String> getGoodsMarks() {
        return goodsMarks;
    }

    public void setGoodsMarks(List<String> goodsMarks) {
        this.goodsMarks = goodsMarks;
    }
}
