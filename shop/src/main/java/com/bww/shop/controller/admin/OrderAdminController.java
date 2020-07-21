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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/v1/order")
public class OrderAdminController extends OrderController {

    private Logger dataloger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;


    //管理员查看所有人订单
    /**
     * 分页查询订单信息
     * @return
     */
    @GetMapping("/page")
    public Result pageOrder(@RequestParam(value = "page", defaultValue = "1")int page,
                            @RequestParam(value = "size", defaultValue = "10")int size,
                            HttpServletRequest request) {
        Result rs = new Result();

        dataloger.info("modul=shop_goods`api=qry page={} size={}", page, size);
        PageHelper.startPage(page,size);

        List<GoodsOrder> orderList = orderService.adminSelectByPage();
        PageInfo<GoodsOrder> pageInfo = new PageInfo<>(orderList);

        Map<String, Object> data = new HashMap<>();
        data.put("total_size", pageInfo.getTotal());
        data.put("total_page", pageInfo.getPages());
        data.put("current_page", pageInfo.getPageNum());
        data.put("dada", pageInfo.getList());

        rs.setResultCode(ResultCode.SUCCESS);
        rs.setData(data);
        return rs;
    }

    //管理员更新某个订单==拒绝，修改订单状态
    @PostMapping("/update")
    public Result updateOrderSatet(@RequestParam String orderId, @RequestParam String state) {

        Result rs = new Result();

        System.out.println("=====" + orderId+"==="+state);
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