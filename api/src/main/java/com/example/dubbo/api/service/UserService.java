package com.example.dubbo.api.service;

import com.example.model.entity.User;

import java.util.List;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
public interface UserService {

    public List<User> findUserList();
    public User findByIdAndUserName(Integer id,String username);
    public String insertUser(User user);
    public String deletByUserName(String username);
}
