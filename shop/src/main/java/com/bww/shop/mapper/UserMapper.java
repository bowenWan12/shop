package com.bww.shop.mapper;

import com.bww.shop.domain.Goods;
import com.bww.shop.domain.User;
import com.bww.shop.provider.GoodsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * user数据访问层
 */
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(int id);

    /**
     * 根据openid查找用户
     * @param openid
     * @return
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User findByOpenId(@Param("openid") String openid);

//    @UpdateProvider(type = GoodsProvider.class, method = "updateGoods")
//    int updateGoodsNameById(User user);

    @Insert("INSERT INTO `user` ( `openid`, `name`, `head_img`', `sex`, `city`, 'phone', 'user_nm', 'pass_wd', 'pass_wd', 'salt', 'type', `create_time`)" +
            "VALUES" +
            "(#{openid},`#{name}, #{head_img}, #{sex}, #{city}, #{phone}, #{user_nm}, #{pass_wd}, #{pass_wd}, #{salt}, {type}, #{create_time});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(User user);
}
