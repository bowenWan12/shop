package com.bww.shop.controller.admin;

import com.bww.shop.domain.Goods;
import com.bww.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/goods")
public class GoodsAdminController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页接口
     * @param page 当前第几页， 默认是第一页
     * @param size 每页显示几条
     * @return
     */
    @GetMapping("page")
    public Object pageGoods(@RequestParam(value = "page", defaultValue = "1")int page,
                            @RequestParam(value = "size", defaultValue = "10")int size){
        return goodsService.findAll();
    }

    /**
     * 根据id找商品
     * @param id
     * @return
     */
    @GetMapping("find_by_id")
    public Object findById(@RequestParam(value = "id", required = true) int id){
        return goodsService.findById(id);
    }

    /**
     * 根据id更新商品
     *
     * @return
     */
    @PutMapping("update_by_id")
    public int updateById(@RequestBody Goods goods){

        return goodsService.updateGoodNameById(goods);
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @PostMapping("save")
    public int save(@RequestBody Goods goods){

        int save = goodsService.save(goods);
        System.out.print("=========="+goods.getId()+"===========");
        return save;
    }
}
