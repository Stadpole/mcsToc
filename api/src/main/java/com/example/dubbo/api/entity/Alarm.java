package com.example.dubbo.api.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Alarm)实体类
 *
 * @author makejava
 * @since 2020-09-24 16:05:27
 */
public class Alarm implements Serializable {
    private static final long serialVersionUID = 614972170697947529L;
    
    private Integer id;
    
    private String warningDetail;
    
    private String equipmentId;
    
    private String type;
    
    private Date time;
    
    private String statusPoint;
    
    private String latchedStatus;
    
    private String open;
    
    private String ack;
    
    private String ackTime;
    
    private String operator;

    private String dateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarningDetail() {
        return warningDetail;
    }

    public void setWarningDetail(String warningDetail) {
        this.warningDetail = warningDetail;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatusPoint() {
        return statusPoint;
    }

    public void setStatusPoint(String statusPoint) {
        this.statusPoint = statusPoint;
    }

    public String getLatchedStatus() {
        return latchedStatus;
    }

    public void setLatchedStatus(String latchedStatus) {
        this.latchedStatus = latchedStatus;
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

    public String getAckTime() {
        return ackTime;
    }

    public void setAckTime(String ackTime) {
        this.ackTime = ackTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}