package com.bww.shop.utils.provider;

import com.bww.shop.domain.Goods;
import org.apache.ibatis.jdbc.SQL;

/**
 * Goods构建动态sql语句
 *
 */
public class GoodsProvider {

    /**
     * 更新goods动态语句
     * @param goods
     * @return
     */
    public String updateGoods(final Goods goods){
        return new SQL(){{
            UPDATE("goods");
            //条件写法.
            if(goods.getPrice() != null){
                SET("price=#{price}");
            }
            if(goods.getName() != null){
                SET("name=#{name}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }
}
