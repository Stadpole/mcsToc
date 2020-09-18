package com.example.dubbo.api.common.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stadpole on 2017/12/19.
 */
@Data
@ToString
public class AlarmRequest implements Serializable {
    private Integer id;  //门限ID
    private String equipment_id;  //设备ID
    private String type; //告警类型 1：链路 2：门限
    private String warning_detail;  //告警详情
    private String status_point;  //状态点
    private String latched_status;  //锁定状态
    private String open;  //开关状态
    private String ack; //是否确认
    private Date ack_time;//确认时间
    private Date time;   //告警时间
    private String operator;//操作username

}