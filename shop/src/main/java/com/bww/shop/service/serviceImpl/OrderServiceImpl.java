package com.bww.shop.service.serviceImpl;

import com.bww.shop.domain.Flow;
import com.bww.shop.domain.GoodsOrder;
import com.bww.shop.mapper.FlowMapper;
import com.bww.shop.mapper.GoodsOrderMapper;
import com.bww.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodsOrderMapper orderMapper;
    @Autowired
    private FlowMapper flowMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int save(GoodsOrder order, List<Flow> flowList) {
        int result = orderMapper.insert(order);

        for (Flow flow : flowList) {
            int saveResult = flowMapper.save(flow);
            result+=saveResult;
        }

        return result;
    }

    @Override
    public List<GoodsOrder> selectByPage(String openid) {

        return orderMapper.findMyOrderList(openid);
    }

    @Override
    public List<GoodsOrder> adminSelectByPage() {
        return orderMapper.adminFindMyOrderList();
    }

    @Override
    public List<GoodsOrder> adminSelectByPage(String openid) {
        return orderMapper.adminFindMyOrderList();
    }

    @Override
    public GoodsOrder selectByOrderId(String orderId) {
        return orderMapper.findByOrderId(orderId);
    }

    @Override
    public int update(GoodsOrder goodsOrder) {
        return orderMapper.updateOrderByOrderId(goodsOrder);
    }

}
