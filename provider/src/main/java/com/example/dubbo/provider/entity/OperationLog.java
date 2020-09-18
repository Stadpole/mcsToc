package com.example.dubbo.provider.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stadpole on 2017/12/19.
 */
@Entity(name="t_operation_log")
public class OperationLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;//用户名
    private String operation_detail;//操作详情
    private String method;//方法
    private String param;//参数
    private Date time;//时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getOperation_detail() {
        return operation_detail;
    }

    public void setOperation_detail(String operation_detail) {
        this.operation_detail = operation_detail;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}