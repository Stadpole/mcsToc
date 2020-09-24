package com.example.dubbo.api.entity;

import java.io.Serializable;

/**
 * (EquipmentDetail)实体类
 *
 * @author makejava
 * @since 2020-09-24 16:05:39
 */
public class EquipmentDetail implements Serializable {
    private static final long serialVersionUID = -63102136039579799L;
    
    private String equipmentId;
    
    private String stationName;
    
    private String equipmentName;
    
    private String driverName;


    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

}