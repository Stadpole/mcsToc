package com.example.dubbo.provider.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.service.UserService;
import com.example.model.entity.User;
import com.example.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mengying on 2017/9/21.
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

@Autowired(required=true)
private UserMapper userMapper;

    @Override
    public List<User> findUserList() {
        return userMapper.selectAll();
    }

    @Override
    public User findByIdAndUserName(Integer id, String username) {
        return null;
    }

    @Override
    public String insertUser(User user) {
        return null;
    }

    @Override
    public String deletByUserName(String username) {
        return null;
    }
}