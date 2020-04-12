package com.bww.shop.mapper;

import com.bww.shop.domain.Flow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * flow数据访问层
 */
public interface FlowMapper {

    @Select("SELECT * FROM flow WHERE id = #{id}")
    Flow findById(int id);

    @Select("SELECT * FROM flow WHERE order_id = #{orderId}")
    List<Flow> findByOrderId(String orderId);

    @Insert("INSERT INTO flow (openid, order_id, flow_id, trans_date, goods_mark, amount_fee, create_time) VALUES" +
            "(#{openid}, #{orderId}, #{flowId}, #{transDate}, #{goodsMark}, #{amountFee}, #{createTime} )")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(Flow flow);
}
