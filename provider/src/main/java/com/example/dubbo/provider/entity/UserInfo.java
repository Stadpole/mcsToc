package com.example.dubbo.provider.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Stadpole on 2017/12/19.
 */
@Entity(name="t_user")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique =true)
    private String username;//帐号

    private String password; //密码;
    private String role;//管理员or用户
    private String description;//用户描述,备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}