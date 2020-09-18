package com.example.dubbo.provider.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stadpole on 2020/5/8 10:37
 */

@Entity(name = "t_alarm")
public class Alarm implements Serializable {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWarning_detail() {
        return warning_detail;
    }

    public void setWarning_detail(String warning_detail) {
        this.warning_detail = warning_detail;
    }

    public String getStatus_point() {
        return status_point;
    }

    public void setStatus_point(String status_point) {
        this.status_point = status_point;
    }

    public String getLatched_status() {
        return latched_status;
    }

    public void setLatched_status(String latched_status) {
        this.latched_status = latched_status;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public Date getAck_time() {
        return ack_time;
    }

    public void setAck_time(Date ack_time) {
        this.ack_time = ack_time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
