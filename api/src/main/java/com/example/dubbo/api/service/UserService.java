package com.example.dubbo.api.service;

import com.example.dubbo.api.common.request.UserInfo;
import com.example.dubbo.api.common.response.BaseResponse;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
//@Path("user")
public interface UserService {
    //TODO:用户登录
//    @Path("1")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes("application/json")
//    public String login(@QueryParam("username") String username, @QueryParam("password") String password);
     BaseResponse login(String username,String password);

    //TODO：根据用户名称查询用户role
     String findByUserName(String usernamne);

    //TODO：分页查询用户列表
     BaseResponse pageUser(Integer page, Integer size, String role);

    //TODO：添加用户
     BaseResponse insertUser(UserInfo user);

    //TODO：通过id删除用户
     BaseResponse deletByID(Integer id);

    //TODO：更新用户
     BaseResponse updateUser(UserInfo user);
}