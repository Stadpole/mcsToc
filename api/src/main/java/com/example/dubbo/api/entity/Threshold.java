package com.example.dubbo.api.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Threshold)实体类
 *
 * @author makejava
 * @since 2020-09-24 16:12:34
 */
public class Threshold implements Serializable {
    private static final long serialVersionUID = -79175270534645625L;
    
    private Integer id;
    
    private String equipmentId;
    
    private String equipmentName;
    
    private String stationName;
    
    private String telemetryName;
    
    private Double highRed;
    
    private Double lowRed;
    
    private Double  highYellow;
    
    private Double lowYellow;
    
    private String dispersedStatus;
    
    private Date time;


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

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTelemetryName() {
        return telemetryName;
    }

    public void setTelemetryName(String telemetryName) {
        this.telemetryName = telemetryName;
    }

    public Double getHighRed() {
        return highRed;
    }

    public void setHighRed(Double highRed) {
        this.highRed = highRed;
    }

    public Double getLowRed() {
        return lowRed;
    }

    public void setLowRed(Double lowRed) {
        this.lowRed = lowRed;
    }

    public Double getHighYellow() {
        return highYellow;
    }

    public void setHighYellow(Double highYellow) {
        this.highYellow = highYellow;
    }

    public Double getLowYellow() {
        return lowYellow;
    }

    public void setLowYellow(Double lowYellow) {
        this.lowYellow = lowYellow;
    }

    public String getDispersedStatus() {
        return dispersedStatus;
    }

    public void setDispersedStatus(String dispersedStatus) {
        this.dispersedStatus = dispersedStatus;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}