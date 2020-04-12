package com.bww.shop.service;

import com.bww.shop.domain.Flow;

import java.util.List;

/**
 * 流水业务类接口
 */
public interface FlowService {

    List<Flow> findByOrderId(String orderId);
}
