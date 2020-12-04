package com.example.dubbo.api.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Telemetry)实体类
 *
 * @author makejava
 * @since 2020-09-24 16:12:26
 */
public class Telemetry implements Serializable {
    private static final long serialVersionUID = 120464970740230807L;
    
    private Integer id;
    
    private String equipmentId;
    
    private String telemetryName;
    
    private String engineeringValue;
    
    private String unit;
    
    private Long time;

    private Double DateTime;

    private String dTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getTelemetryName() {
        return telemetryName;
    }

    public void setTelemetryName(String telemetryName) {
        this.telemetryName = telemetryName;
    }

    public String getEngineeringValue() {
        return engineeringValue;
    }

    public void setEngineeringValue(String engineeringValue) {
        this.engineeringValue = engineeringValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getDateTime() {
        return DateTime;
    }

    public void setDateTime(Double dateTime) {
        DateTime = dateTime;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }
}