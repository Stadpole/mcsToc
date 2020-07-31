package com.example.dubbo.api.common.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Stadpole on 2017/12/19.
 */
@Data
@ToString
public class UserInfoRequest implements Serializable {
    private Integer id;
    private String username;//帐号
    private String password; //密码;
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    private String phone_number;//用户手机号
    private String email;//用户邮箱
    private String role;//管理员or用户
    private String job;//用户职位

}