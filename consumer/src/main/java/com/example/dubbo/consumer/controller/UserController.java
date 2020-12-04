package com.example.dubbo.consumer.controller;

import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.OperationLog;
import com.example.dubbo.api.entity.UserInfo;
import com.example.dubbo.api.service.OperationLogService;
import com.example.dubbo.api.service.UserService;
import com.example.dubbo.consumer.common.BaseCommonController;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Stadpole on 2017/9/21.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @DubboReference(version = "1.0.0")
    private UserService userService;
    @DubboReference(version = "1.0.0")
    private OperationLogService operationLogService;

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password) {

        BaseResponse response = new BaseResponse();
        try {
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                response = userService.login(username, password);
                if (StringUtils.isNotBlank(response.getMsg())) {
                    String idAndrole = userService.findByUserName(username);
                    if (idAndrole != null && response.getMsg().equals("success")) {
                        OperationLog operationLog = new OperationLog();
                        String string[] = idAndrole.split("_");
                        operationLog.setUserId(Integer.parseInt(string[0]));
                        operationLog.setOperationDetail("登录成功");
                        operationLog.setMethod("用户操作");
                        operationLog.setParam("用户名称:" + username);
                        operationLog.setTime(new Date().getTime());
                        operationLogService.save(operationLog);
                        return sendLoginMessage(response.getMsg(), string[0], string[1]);
                    } else {
                        return sendLoginMessage(response.getMsg(), "", "");
                    }
                }
            } else {
                return sendLoginMessage("用户名密码不能为空", "", "");
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return sendLoginMessage("登录失败", "", "");
    }

    /**
     * 用户列表查询-分页查询
     *
     * @return
     */

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String Page(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String role) {
        if (page == null || size == null || page <= 0 || size <= 0) {
            page = 1;
            size = 2;
        }
        //TODO:调用服务提供方userService提供的列表查询-分页查询功能
        try {
            Gson gson = new Gson();
            BaseResponse response = userService.pageUser(page, size, role);
            if (response != null && response.getCode().equals(0)) {
               // log.info("用户列表+" + response.getData() + "----");
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
    public String insertUser(@RequestParam Integer id, @RequestBody UserInfo user) {
        //TODO:调用服务提供方userService提供的添加用户
        try {
            BaseResponse response = userService.insertUser(user);
            if (response.getCode() == 0) {
                OperationLog operationLog = new OperationLog();
                operationLog.setUserId(id);
                operationLog.setOperationDetail("新增用户成功");
                operationLog.setMethod("用户操作");
                operationLog.setParam("用户名:" + user.getUsername() + " 角色:" + user.getRole() + " 描述:" + user.getDescription());
                operationLog.setTime(System.currentTimeMillis());
                operationLogService.save(operationLog);
                return sendMessage("0", "success", response.getData());
            }else if (response.getCode()==1){
                return sendMessage("1", "repeat", response.getMsg());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }

    /**
     * 更新用户
     *
     * @param id 当前登录的用户id
     * @return
     */

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestParam Integer id, @RequestBody UserInfo user) {
        //TODO:调用服务提供方userService提供的更新用户
        try {
            BaseResponse response = userService.updateUser(user);
            if (response.getCode() == 0) {
                OperationLog operationLog = new OperationLog();
                operationLog.setUserId(id);
                operationLog.setOperationDetail("更新用户成功，字段为空说明该信息未修改");
                operationLog.setMethod("用户操作");
                operationLog.setParam("用户名:" + user.getUsername() + " 角色:" + user.getRole() + " 描述:" + user.getDescription());
                operationLog.setTime(System.currentTimeMillis());
                operationLogService.save(operationLog);
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
     * @param userId 为当前登录的用户id
     * @param id     要删除的用户id
     * @return
     */

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam Integer userId, @RequestParam Integer id) {
        //TODO:调用服务提供方userService提供的删除用户
        try {
            OperationLog operationLog = new OperationLog();
            operationLog.setUserId(userId);
            operationLog.setOperationDetail("删除用户成功");
            operationLog.setMethod("用户操作");
            operationLog.setParam("用户名:" + userService.findUserById(id).getUsername());
            operationLog.setTime(System.currentTimeMillis());
            BaseResponse response = userService.deletByID(id);
            if (response.getCode() == 0) {
                operationLogService.save(operationLog);
            }
            return sendMessage("0", "success", response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }

}

