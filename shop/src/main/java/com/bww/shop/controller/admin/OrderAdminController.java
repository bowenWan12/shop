package com.bww.shop.controller.admin;

import com.bww.shop.common.ResultCode;
import com.bww.shop.common.constant.OrderEnums;
import com.bww.shop.controller.OrderController;
import com.bww.shop.domain.Goods;
import com.bww.shop.domain.GoodsOrder;
import com.bww.shop.domain.Result;
import com.bww.shop.domain.User;
import com.bww.shop.dto.OrderDto;
import com.bww.shop.service.GoodsService;
import com.bww.shop.service.OrderService;
import com.bww.shop.service.UserService;
import com.bww.shop.utils.DateUtils;
import com.bww.shop.utils.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/admin/api/v1/order")
//@RequestMapping("/api/v1/order")
public class OrderAdminController extends OrderController {

    private Logger dataloger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;


    //管理员查看所有人订单


    //管理员更新某个订单==拒绝，修改订单状态
    @PostMapping("/update")
    public Result updateOrderSatet(String orderId, String state) {

        Result rs = new Result();

        GoodsOrder goodsOrder = orderService.selectByOrderId(orderId);
        if (goodsOrder == null || StringUtils.isBlank(goodsOrder.getOrderId())) {
            rs.setResultCode(ResultCode.RESULE_DATA_NONE);
        }
        goodsOrder.setTransDate(DateUtils.formatDateTo14Str(new Date()));
        goodsOrder.setState(state);
        dataloger.info("modul=shop_order`api=update orderId={} state={}", orderId, state);
        int update = orderService.update(goodsOrder);

        if (update ==1) {
            rs.simple().put("state", state);
            rs.setResultCode(ResultCode.SUCCESS);
        }


        return rs;
    }

}