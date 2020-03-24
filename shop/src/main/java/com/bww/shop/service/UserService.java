package com.bww.shop.service;

import com.bww.shop.domain.User;

public interface UserService {

    int saveWechatUser(User user);

    User findById(int id);

    User findByOpenId(String openid);

}
