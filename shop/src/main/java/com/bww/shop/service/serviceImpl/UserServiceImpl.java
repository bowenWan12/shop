package com.bww.shop.service.serviceImpl;

import com.bww.shop.domain.User;
import com.bww.shop.mapper.UserMapper;
import com.bww.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveWechatUser(User user) {

        return userMapper.save(user);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByOpenId(String openid) {
        return userMapper.findByOpenId(openid);
    }
}
