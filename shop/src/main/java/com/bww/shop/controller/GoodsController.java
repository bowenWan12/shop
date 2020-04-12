package com.bww.shop.controller;

import com.bww.shop.common.ResultCode;
import com.bww.shop.common.constant.GoodsEnums;
import com.bww.shop.domain.Goods;
import com.bww.shop.domain.Result;
import com.bww.shop.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/goods")
public class GoodsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataloger = LoggerFactory.getLogger("dataLogger");

   @Autowired
   private GoodsService goodsService;


    /**
     * 分页接口
     * @param page 当前第几页， 默认是第一页
     * @param size 每页显示几条
     * @return
     */
   @GetMapping("/page")
   public Result pageGoods(@RequestParam(value = "page", defaultValue = "1")int page,
                           @RequestParam(value = "size", defaultValue = "10")int size){
       dataloger.info("modul=shop_goods`api=qry page={} size={}", page, size);
       Result rs = new Result();
       PageHelper.startPage(page,size);
       List<Goods> goodsList = goodsService.findAll();
       PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);

       Map<String, Object> data = new HashMap<>();
       data.put("total_size", pageInfo.getTotal());
       data.put("total_page", pageInfo.getPages());
       data.put("current_page", pageInfo.getPageNum());
       data.put("dada", pageInfo.getList());

       rs.setResultCode(ResultCode.SUCCESS);
       rs.setData(data);
       return rs;
   }

    /**
     * 根据id找商品
     * @param id
     * @return
     */
   @GetMapping("/find_by_id")
   public Result findById(@RequestParam(value = "id", required = true) int id){
       Result rs = new Result();
       rs.setResultCode(ResultCode.SUCCESS);
       Goods goods = goodsService.findById(id);
       rs.setData(goods);
       return rs;
   }

    /**
     * 根据mark查找商品
     * @param mark
     * @return
     */
   @GetMapping("/find_by_mark")
   public Result findByMark(@RequestParam(value = "mark", required = true) String mark){

       Result rs = new Result();
       Goods goods = goodsService.findByMark(mark);

       if (goods == null) {
           rs.setResultCode(ResultCode.DATA_IS_WRONG);
           return rs;
       }

       rs.setResultCode(ResultCode.SUCCESS);
       rs.setData(goods);
       return Result.success();
   }

}
