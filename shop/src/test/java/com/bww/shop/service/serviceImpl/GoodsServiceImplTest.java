package com.bww.shop.service.serviceImpl;

import com.bww.shop.domain.Goods;
import com.bww.shop.mapper.GoodsMapper;
import com.bww.shop.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsMapper goodsMapper;
    @Test
    public void findAll() {
        List<Goods> all = goodsService.findAll();
        assertNotNull(all);
        for (Goods goods : all) {
            System.out.println(goods);
        }

    }

    @Test
    public void findById() {
        Goods byId = goodsService.findById(2);
        System.out.println(byId);
    }

    @Test
    public void updateGoodNameById() {
    }

    @Test
    public void save() {
    }
}