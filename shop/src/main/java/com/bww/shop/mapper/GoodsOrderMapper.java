package com.bww.shop.mapper;

import com.bww.shop.domain.GoodsOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单dao层
 */
public interface GoodsOrderMapper {

    @Insert("INSERT INTO `goodsOrder`(`openid`, `nick_name`, `head_img`, `order_id`, `trans_date`, " +
            "`address_info`, `postscript`, `state`, `total_fee`, `del`, `update_time`, `create_time`) VALUES " +
            "(#{openid}, #{nickName}, #{headImg}, #{orderId}, " +
            "#{transDate}, #{addressInfo}, #{postscript}, #{state}, #{totalFee}, #{del}, #{updateTime}, #{createTime});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(GoodsOrder order);

    @Select("select * from goodsOrder where order_id = #{orderId}")
    GoodsOrder findByOrderId(@Param("orderId") String orderId);


    @Select("select * from goodsOrder where flow_id = #{flow_id} and del = 0")
    GoodsOrder findByFlowId(@Param("flow_id") String flowId);

    @Update("update order set del=0 where order_id=#{order_id} and openid=#{openid}")
    int del(@Param("order_id") String orderId, @Param("openid") String openid);


    @Select("select * from goodsOrder where openid=#{openid}")
    List<GoodsOrder> findMyOrderList(String openid);

    @Update("update goodsOrder set state=#{state} where order_id = #{orderId}")
    int updateOrderByOrderId(GoodsOrder order);
}
