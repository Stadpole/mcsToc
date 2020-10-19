package com.example.dubbo.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.io.Serializable;

/**
 * (OperationLog)实体类
 *
 * @author makejava
 * @since 2020-09-24 16:12:02
 */
public class OperationLog implements Serializable {
    private static final long serialVersionUID = 127519467667836839L;
    
    private Integer id;
    
    private Integer userId;
    
    private String operationDetail;
    
    private String method;
    
    private String param;

    @JSONField(format="yyyy-MM-dd")
    private Date time;

    //不用持久化
    private String username;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}