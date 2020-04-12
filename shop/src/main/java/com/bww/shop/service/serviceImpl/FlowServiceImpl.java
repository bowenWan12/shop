package com.bww.shop.service.serviceImpl;

import com.bww.shop.domain.Flow;
import com.bww.shop.mapper.FlowMapper;
import com.bww.shop.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private FlowMapper flowMapper;
    @Override
    public List<Flow> findByOrderId(String orderId) {
        return flowMapper.findByOrderId(orderId);
    }
}
