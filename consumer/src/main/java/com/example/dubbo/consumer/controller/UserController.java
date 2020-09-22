package com.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.api.common.request.UserInfoRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.UserService;
import com.example.dubbo.consumer.common.AOP.MyLog;
import com.example.dubbo.consumer.common.BaseCommonController;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Stadpole on 2017/9/21.
 */

@Api(tags = "用户API")
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
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@RequestParam String username,@RequestParam String password) {
        BaseResponse response=new BaseResponse();
        try {
            if(StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)) {
                response = userService.login(username, password);
                if (StringUtils.isNotBlank(response.getMsg())) {
                    String idAndrole=userService.findByUserName(username);
                    if(idAndrole!=null&&response.getMsg().equals("success")) {
                        String string[] = idAndrole.split("_");
                        return sendLoginMessage(response.getMsg(), string[0], string[1]);
                    }
                    else{
                        return sendLoginMessage(response.getMsg(), "","");
                    }
                }
            }else {
                return sendLoginMessage("用户名密码不能为空","","");
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return sendLoginMessage("登录失败","","");
    }
    /**
     * 用户列表查询-分页查询
     *
     * @return
     */

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String Page(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String role) {
        if (page == null || size == null || page <= 0 || size <= 0) {
            page = 1;
            size = 2;
        }
        //TODO:调用服务提供方userService提供的列表查询-分页查询功能
        try {
            Gson gson=new Gson();
            BaseResponse response = userService.pageUser(page, size, role);
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
    @MyLog(value = "新增用户")
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
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
    @MyLog(value = "更新用户信息")
    @ApiOperation(value = "更新用户信息", notes = "根据用户id更新用户信息，必须包括id")
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
    @MyLog(value = "删除用户信息")
    @ApiOperation(value = "删除用户信息", notes = "根据用户id删除用户信息，必须包括id")
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

