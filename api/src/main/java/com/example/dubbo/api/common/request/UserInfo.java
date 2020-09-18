package com.example.dubbo.api.common.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Stadpole on 2017/12/19.
 */
@Data
@ToString
public class UserInfo implements Serializable {
    private Integer id;
    private String username;//帐号
    private String password; //密码;
    private String role;//管理员or用户
    private String description;//用户描述,备注

}