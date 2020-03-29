package com.bww.shop.mapper;

import com.bww.shop.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单dao层
 */
public interface OrderMapper {

    @Insert("INSERT INTO `order`(`openid`, `nick_name`, `head_img`, `order_id`, `flow_id`, `trans_date`, " +
            "`goods_mark`, `goods_info`, `state`, `total_fee`, `del`, `update_time`, `create_time`) VALUES " +
            "(#{openid}, #{nickName}, #{headImg}, #{orderId}, #{flowId}, " +
            "#{tranDate}, #{goodsMark}, #{goodsInfo}, #{state}, #{totalFee}, #{del}, #{updateTime}, #{createTime});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order order);

    @Select("select * from order where id = #{order_id}")
    Order findById(@Param("order_id") int id);


    @Select("select * from order where flow_id = #{flow_id} and del = 0")
    Order findByFlowId(@Param("flow_id") String flowId);

    @Update("update order set del=0 where order_id=#{order_id} and openid=#{openid}")
    int del(@Param("order_id") String orderId, @Param("openid") String openid);


    @Select("select * from order where openid=#{openid}")
    List<Order> findMyOrderList(String openid);

    @Update("update order set state=#{state} where order_id = #{orderId} and del = 0")
    int ipdateOrderByOrderId(Order order);
}
