package com.bww.shop.service.serviceImpl;

import ch.qos.logback.core.net.SyslogOutputStream;
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
        System.out.println(user);
        return userMapper.save(user);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByOpenId(String openid) {
        if (userMapper.findByOpenId(openid) == null) {
            return new User();
        } else {
            return userMapper.findByOpenId(openid);
        }
    }

    @Override
    public int updateUserAddressInfoByOpenid(String addressInfo, String openid) {
        return userMapper.updateAddressInfoByOpenid(addressInfo, openid);
    }
}
