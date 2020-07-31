package com.example.dubbo.api.service;

import com.example.dubbo.api.common.request.UserInfoRequest;
import com.example.dubbo.api.common.response.BaseResponse;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
public interface UserService {

    //TODO：查询用户列表
    public BaseResponse findUserList();

    //TODO：分页查询用户列表
    public BaseResponse pageUser(Integer page, Integer size, String role, String job);

    //TODO：添加用户
    public BaseResponse insertUser(UserInfoRequest user);

    //TODO：通过id删除用户
    public BaseResponse deletByID(Integer id);

    //TODO：更新用户
    public BaseResponse updateUser(UserInfoRequest user);
}