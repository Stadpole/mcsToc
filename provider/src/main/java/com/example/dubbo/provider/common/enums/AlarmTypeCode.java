package com.example.dubbo.provider.common.enums;
/**
 * Created by Administrator on 2020/7/21.
 */

/**
 * @Author:Stadpole
 * @Date: 2020/7/21 14:01
 **/
public enum AlarmTypeCode {

    TelemetryAlarm(1,"遥测告警"),
    LinkAlarm(2,"链路告警");

    private Integer code;
    private String msg;

    AlarmTypeCode(Integer code, String msg) {
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





















