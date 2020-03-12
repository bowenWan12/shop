package com.bww.shop.mapper;

import com.bww.shop.domain.Goods;
import com.bww.shop.provider.GoodsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * goods数据访问层
 */
public interface GoodsMapper {

    @Select("select * from goods")
    List<Goods> findAll();

    @Select("SELECT * FROM goods WHERE id = #{id}")
    Goods findById(int id);

    @UpdateProvider(type = GoodsProvider.class, method = "updateGoods")
    int updateGoodsNameById(Goods goods);

    @Insert("INSERT INTO goods (name, image_path, description, price, state, num) VALUES" +
            "(#{name}, #{imagePath}, #{description}, #{price}, #{state}, #{num} )")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(Goods goods);
}
