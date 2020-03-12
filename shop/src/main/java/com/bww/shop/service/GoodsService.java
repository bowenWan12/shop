package com.bww.shop.service;

import com.bww.shop.domain.Goods;

import java.util.List;

/**
 * 商品业务类接口
 */
public interface GoodsService {
    List<Goods> findAll();

    Goods findById(int id);

    int updateGoodNameById(Goods goods);

    int save(Goods goods);
}
