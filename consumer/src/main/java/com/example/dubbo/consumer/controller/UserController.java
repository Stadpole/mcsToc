package com.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.api.service.UserService;
import com.example.dubbo.consumer.common.BaseCommonController;
import com.example.model.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseCommonController {
    @Reference(version = "1.0.0")
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> findOne() {
        return userService.findUserList();
    }

}

