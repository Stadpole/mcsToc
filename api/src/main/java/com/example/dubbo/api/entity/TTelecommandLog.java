package com.example.dubbo.api.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TTelecommandLog)实体类
 *
 * @author makejava
 * @since 2020-09-24 16:12:10
 */
public class TTelecommandLog implements Serializable {
    private static final long serialVersionUID = 498027807988927499L;
    
    private Integer id;
    
    private Integer userId;
    
    private String telecommandName;
    
    private Integer equipmentId;
    
    private String parameter;
    
    private Date time;
    
    private Integer operationFeedback;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTelecommandName() {
        return telecommandName;
    }

    public void setTelecommandName(String telecommandName) {
        this.telecommandName = telecommandName;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getOperationFeedback() {
        return operationFeedback;
    }

    public void setOperationFeedback(Integer operationFeedback) {
        this.operationFeedback = operationFeedback;
    }

}