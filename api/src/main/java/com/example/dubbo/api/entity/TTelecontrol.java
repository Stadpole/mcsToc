package com.example.dubbo.api.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TTelecontrol)实体类
 *
 * @author makejava
 * @since 2020-09-24 16:12:19
 */
public class TTelecontrol implements Serializable {
    private static final long serialVersionUID = 542229065147615041L;
    
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
        this.telecontrolName = telecontrolName;
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

}