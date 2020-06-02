package com.bww.shop.provider;

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
    public String updateGoodsById(final Goods goods){
        return new SQL(){{
            UPDATE("goods");
            //条件写法.
            if(goods.getPrice() != null){
                SET("price=#{price}");
            }
            if(goods.getName() != null){
                SET("name=#{name}");
            }
            if(goods.getDescription() != null){
                SET("description=#{description}");
            }
            if(goods.getState() != null){
                SET("state=#{state}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }

    public String updateGoodsByMark(final Goods goods){
        return new SQL(){{
            UPDATE("goods");
            //条件写法.
            if(goods.getPrice() != null){
                SET("price=#{price}");
            }
            if(goods.getName() != null){
                SET("name=#{name}");
            }
            if(goods.getDescription() != null){
                SET("description=#{description}");
            }
            if(goods.getNum() != null){
                SET("num=#{num}");
            }
            WHERE("mark=#{mark}");
        }}.toString();
    }
}
