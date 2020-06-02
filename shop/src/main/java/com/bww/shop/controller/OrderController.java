package com.bww.shop.controller;

import com.bww.shop.common.ResultCode;
import com.bww.shop.common.constant.OrderEnums;
import com.bww.shop.domain.*;
import com.bww.shop.service.FlowService;
import com.bww.shop.service.GoodsService;
import com.bww.shop.service.OrderService;
import com.bww.shop.service.UserService;
import com.bww.shop.utils.DateUtils;
import com.bww.shop.utils.IpUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.annotations.JsonAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.commons.lang3.StringUtils.leftPad;

@RestController
//@RequestMapping("/user/api/v1/order")
@RequestMapping("/api/v1/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataloger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private FlowService flowService;
    private static final Gson gson = new Gson();

    @PostMapping("/add")
    public Result saveOrder(@RequestParam(value = "goodsMarks", required = true) @RequestBody List<String> goodsMarks,
                            String addressInfo,
                            @RequestParam(value = "totalFee", required = true) String totalFee,
                            String postscript,
                            HttpServletRequest request) {

        String ip = IpUtils.getIpAddr(request);
        if (goodsMarks.size() < 1) {
            return Result.error(ResultCode.PARAM_NOT_COMPLETE,"商品信息为null");
        }
//        Integer userId = (Integer) request.getAttribute("user_id");
//        if (userId == null) {
//            return Result.error(ResultCode.RESULE_DATA_NONE, "userId is null");
//        }
        int userId = 1;

        //查找用户信息
        User user = userService.findById(userId);
        if (user == null) {
            return Result.error(ResultCode.RESULE_DATA_NONE, "用户信息未找到");
        }

        if (addressInfo == null || addressInfo == "") {
            addressInfo = user.getAddressInfo();
        }

        if (addressInfo == null || addressInfo == "") {
            return Result.error(ResultCode.RESULE_DATA_NONE, "用户收货信息未找到");
        }

        GoodsOrder order = new GoodsOrder();
        order.setOpenid(user.getOpenid());
        order.setNickName(user.getName());
        order.setHeadImg(user.getHeadImg());
        order.setOrderId(GenerateOrderStr.nextOid(ip));
        order.setTransDate(DateUtils.formatDateTo14Str(new Date()));
        order.setAddressInfo(addressInfo);
        order.setPostscript(postscript);
        order.setState(OrderEnums.OrderState._0.getCode());
        order.setTotalFee(new BigDecimal(totalFee));
        order.setCreateTime(new Date());

        List<Flow> flowList = new ArrayList<Flow>();
        for (int i = 0; i < goodsMarks.size(); i++) {
            //查找商品信息
            //TODO 对其格式进行严格检查
            String[] split = goodsMarks.get(i).split("[|]");
            Goods goods = goodsService.findByMark(split[0]);
            if (goods == null) {
                return Result.error(ResultCode.RESULE_DATA_NONE, "商品信息未找到");
            }
            for (int j = 0; j < Integer.parseInt(split[1]); j++) {
                Flow flow = new Flow();
                flow.setOpenid(user.getOpenid());
                flow.setFlowId(GenerateOrderStr.nextFlowId(goods.getMark()));
                flow.setOrderId(order.getOrderId());
                flow.setTransDate(DateUtils.formatDateTo14Str(new Date()));
                flow.setGoodsMark(goods.getMark());
                flow.setGoodsName(goods.getName());
                flow.setAmountFee(goods.getPrice());
                flow.setCreateTime(new Date());

                flowList.add(flow);
            }


        }

        //TODO 检查总金额是否正确
        int result = orderService.save(order, flowList);

        if (result == flowList.size() + 1) {
            return Result.success();
        } else {
            return Result.error(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
        }
    }


    /**
     * 根据订单号查询流水明细
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@RequestParam(value = "orderId", required = true) String orderId) {
        Result rs = new Result();
        List<Flow> flowList = flowService.findByOrderId(orderId);
        Map<String, List<Flow>> flowMap = new HashMap<>();
        for (Flow flow:flowList) {
            if (!flowMap.containsKey(flow.getGoodsMark())) {
                List<Flow> f = new ArrayList<>();
                f.add(flow);
                flowMap.put(flow.getGoodsMark(), f);
            } else {
                List<Flow> flows = flowMap.get(flow.getGoodsMark());
                flows.add(flow);
            }
        }




        rs.setData(flowMap);
        rs.setCode(Result.success().getCode());
        return rs;
    }


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
//        Integer userId = (Integer) request.getAttribute("user_id");
//        if (userId == null) {
//            return Result.error(ResultCode.RESULE_DATA_NONE, "userId is null");
//        }
        int userId =1;
        //查找用户信息
        User user = userService.findById(userId);
        if (user == null) {
            return Result.error(ResultCode.RESULE_DATA_NONE, "用户信息未找到");
        }

        List<GoodsOrder> orderList = orderService.selectByPage(user.getOpenid());
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



    public static class GenerateOrderStr{
        private static final transient AtomicInteger order_index = new AtomicInteger(0);
        public static String nextOid(String ip){
            String[] ips = ip.split("\\.");
            ip =leftPad(ips[2],3,'0')+leftPad(ips[3],3,'0');
            SimpleDateFormat foramter = new SimpleDateFormat("yyMMddHHmmssSSS");
            String time = foramter.format(new Date());
            order_index.compareAndSet(999, 1);
            return ip+time+leftPad(String.valueOf(order_index.incrementAndGet()%999),3,'0')+"000000";
        }

        public static String nextFlowId(String goodsMark){
            SimpleDateFormat foramter = new SimpleDateFormat("yyMMddHHmmssSSS");
            String time = foramter.format(new Date());
            order_index.compareAndSet(999, 1);
            return time+leftPad(String.valueOf(order_index.incrementAndGet()%999),3,'0')+goodsMark.substring(24);
        }
    }

}