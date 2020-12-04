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
    
    private Long time;
    
    private String statusPoint;
    
    private String latchedStatus;
    
    private String open;
    
    private String ack;
    
    private Long ackTime;
    
    private String operator;

    private String dateTime;

    private String telemetryName;

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getAckTime() {
        return ackTime;
    }

    public void setAckTime(Long ackTime) {
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

    public String getTelemetryName() {
        return telemetryName;
    }

    public void setTelemetryName(String telemetryName) {
        this.telemetryName = telemetryName;
    }
}