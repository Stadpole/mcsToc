package com.example.dubbo.api.common.enums;

/**
 * @Author:Stadpole
 * @Date: 2020/7/21 14:01
 **/
public enum  StatusCode {

    Success(0,"success"),
    Repeat(1,"repeat"),
    Fail(-1,"fail"),
    InvalidParams(200,"无效的参数");

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}





















