package com.example.dubbo.api.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (AlarmRecovery)实体类
 *
 * @author makejava
 * @since 2020-09-24 16:05:27
 */

public class AlarmRecovery implements Serializable {
    private static final long serialVersionUID = 614972170697947529L;

    private Integer id;

    private String warningDetail;

    private String equipmentId;

    private String type;

    private Long time;

    private String latchedStatus;

    private String open;

    private String ack;

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
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