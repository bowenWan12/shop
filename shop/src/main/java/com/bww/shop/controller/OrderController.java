package com.bww.shop.controller;

import com.bww.shop.common.ResultCode;
import com.bww.shop.domain.Goods;
import com.bww.shop.domain.Result;
import com.bww.shop.domain.User;
import com.bww.shop.dto.OrderDto;
import com.bww.shop.service.GoodsService;
import com.bww.shop.service.OrderService;
import com.bww.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/user/api/v1/order")
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    @GetMapping("add")
    public Result saveOrder(@RequestParam(value = "goodsMarks",required = true)String[] goodsMarks,
                            HttpServletRequest request) {
//        String ip = IpUtils.getIpAddr(request);
        if (goodsMarks.length < 1) {
            return Result.error(ResultCode.PARAM_NOT_COMPLETE);
        }
        Integer userId = (Integer) request.getAttribute("user_id");
        if (userId == null){
            return Result.error(ResultCode.RESULE_DATA_NONE, "userId is null");
        }

        //查找用户信息
        User user = userService.findById(userId);
        if (user == null){
            return Result.error(ResultCode.RESULE_DATA_NONE, "用户信息未找到");
        }

        //查找商品信息
        for (int i = 0; i < goodsMarks.length; i++) {
            Goods goods= goodsService.findByMark(goodsMarks[i]);
            if (goods == null){
                return Result.error(ResultCode.RESULE_DATA_NONE, "商品信息未找到");
            }
            OrderDto orderDto = new OrderDto();
            orderDto.setOpenid(user.getOpenid());
            orderDto.setNickName(user.getName());
            orderDto.setHeadImg(user.getHeadImg());
            orderDto.setOrderId("");
            orderDto.setGoodsMark(goodsMarks[i]);
        }




        return Result.success();
    }
}
