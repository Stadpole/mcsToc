package com.example.model.entity;

import java.util.Date;

public class Telecontrol {
    private Integer id;

    private String telecontrolName;

    private String parameter;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelecontrolName() {
        return telecontrolName;
    }

    public void setTelecontrolName(String telecontrolName) {
        this.telecontrolName = telecontrolName == null ? null : telecontrolName.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}