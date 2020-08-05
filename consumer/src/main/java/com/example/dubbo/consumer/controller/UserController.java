package com.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.api.common.request.UserInfoRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.UserService;
import com.example.dubbo.consumer.common.BaseCommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Reference(version = "1.0.0")
    private UserService userService;

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password) {
        BaseResponse response=new BaseResponse();
        try {
            response = userService.login(username,password);
            if(response.getCode()==0) {
                return sendMessage("0", "success", response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage("1",response.getData());
    }
    /**
     * 用户列表查询-分页查询
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String Page(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String role, @RequestParam String job) {
        if (page == null || size == null || page <= 0 || size <= 0) {
            page = 1;
            size = 2;
        }
        //TODO:调用服务提供方userService提供的列表查询-分页查询功能
        try {
            BaseResponse response = userService.pageUser(page, size, role, job);
            if (response != null && response.getCode().equals(0)) {
                return sendMessage("0", "success", response.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertUser(@RequestBody UserInfoRequest user) {
        //TODO:调用服务提供方userService提供的添加用户
        try {
            BaseResponse response = userService.insertUser(user);
            if(response.getCode()==0) {
                return sendMessage("0", "success", response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }

    /**
     * 更新用户
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody UserInfoRequest user) {
        //TODO:调用服务提供方userService提供的更新用户
        try {
            BaseResponse response = userService.updateUser(user);
            if(response.getCode()==0) {
                return sendMessage("0", "success", response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam Integer id) {
        //TODO:调用服务提供方userService提供的删除用户
        try {
            BaseResponse response = userService.deletByID(id);
            if(response.getCode()==0) {
                return sendMessage("0", "success", response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
}

