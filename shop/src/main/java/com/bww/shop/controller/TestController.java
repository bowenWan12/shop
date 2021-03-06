package com.bww.shop.controller;

import com.bww.shop.config.ShopConfig;
import com.bww.shop.domain.Result;
import com.bww.shop.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ShopConfig shopConfig;



    @RequestMapping("config")
    public String test(){

        return shopConfig.getAppid()+"|"+shopConfig.getUserNm()+"|"+shopConfig.getPassWd();
    }

    @Autowired
    private GoodsMapper goodsMapper;

    @RequestMapping("db_qry")
    public Result qryAllGoods(){

        return Result.success(goodsMapper.findAll());
    }
}
