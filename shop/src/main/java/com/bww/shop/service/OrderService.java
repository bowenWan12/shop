package com.bww.shop.service;

import com.bww.shop.domain.Flow;
import com.bww.shop.domain.GoodsOrder;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 订单接口
 */
public interface OrderService {
    int save(GoodsOrder order, List<Flow> flowList);

    List<GoodsOrder> selectByPage(String openid);

    List<GoodsOrder> adminSelectByPage();

    List<GoodsOrder> adminSelectByPage(String openid);

    GoodsOrder selectByOrderId(String orderId);

    int update(GoodsOrder goodsOrder);
}
