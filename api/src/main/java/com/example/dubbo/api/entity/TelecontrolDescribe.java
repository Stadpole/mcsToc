package com.example.dubbo.api.entity;

import java.io.Serializable;

/**
 * (TelecontrolDescribe)实体类
 *
 * @author makejava
 * @since 2020-09-28 18:08:19
 */
public class TelecontrolDescribe implements Serializable {
    private static final long serialVersionUID = -66258516987030623L;
    
    private String equipmentId;
    
    private String commandNumber;
    
    private String describeInfo;


    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getCommandNumber() {
        return commandNumber;
    }

    public void setCommandNumber(String commandNumber) {
        this.commandNumber = commandNumber;
    }

    public String getDescribeInfo() {
        return describeInfo;
    }

    public void setDescribeInfo(String describeInfo) {
        this.describeInfo = describeInfo;
    }
}