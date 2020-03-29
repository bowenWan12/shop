package com.bww.shop.service;

import com.bww.shop.domain.Order;
import com.bww.shop.dto.OrderDto;

/**
 * 订单接口
 */
public interface OrderService {
    Order save(OrderDto orderDto);
}
