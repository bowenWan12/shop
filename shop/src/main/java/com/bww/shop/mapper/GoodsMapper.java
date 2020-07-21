package com.bww.shop.mapper;

import com.bww.shop.domain.Goods;
import com.bww.shop.provider.GoodsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * goods数据访问层
 */
public interface GoodsMapper {

    @Select("select * from goods where state = '0'")
    List<Goods> findAll();

    @Select("SELECT * FROM goods WHERE id = #{id}")
    Goods findById(int id);

    @Select("SELECT * FROM goods WHERE mark = #{mark}")
    Goods findByMark(String mark);

    @UpdateProvider(type = GoodsProvider.class, method = "updateGoodsById")
    int updateGoodsNameById(Goods goods);

    @UpdateProvider(type = GoodsProvider.class, method = "updateGoodsByMark")
    int updateGoodsNameByMark(Goods goods);

    @Insert("INSERT INTO goods (mark, image_path, state) VALUES" +
            "(#{mark}, #{imagePath}, #{state})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(Goods goods);
}
