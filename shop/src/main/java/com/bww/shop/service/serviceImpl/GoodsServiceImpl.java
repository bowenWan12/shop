package com.bww.shop.service.serviceImpl;

import com.bww.shop.domain.Goods;
import com.bww.shop.mapper.GoodsMapper;
import com.bww.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public Goods findById(int id) {
        return goodsMapper.findById(id);
    }

    @Override
    public Goods findByMark(String mark) {
        return goodsMapper.findByMark(mark);
    }

    @Override
    public int updateGoodNameById(Goods goods) {
        return goodsMapper.updateGoodsNameById(goods);
    }

    @Override
    public int updateGoodNameByMark(Goods goods) {
        return goodsMapper.updateGoodsNameByMark(goods);
    }

    @Override
    public int save(Goods goods) {
        return goodsMapper.save(goods);
    }
}
