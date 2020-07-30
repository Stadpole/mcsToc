package com.example.model.entity;

import java.util.Date;

public class TelemetryHistory {
    private Integer id;

    private Integer equipmentId;

    private Integer telemetryId;

    private String rawValue;

    private String engineeringValue;

    private String description;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getTelemetryId() {
        return telemetryId;
    }

    public void setTelemetryId(Integer telemetryId) {
        this.telemetryId = telemetryId;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue == null ? null : rawValue.trim();
    }

    public String getEngineeringValue() {
        return engineeringValue;
    }

    public void setEngineeringValue(String engineeringValue) {
        this.engineeringValue = engineeringValue == null ? null : engineeringValue.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}