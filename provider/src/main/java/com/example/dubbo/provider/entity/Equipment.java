package com.example.dubbo.provider.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stadpole on 2020/5/8 10:37
 */

@Entity(name = "equipment")
public class Equipment implements Serializable {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer eq_Id;
    private Integer tm_Id;
    private String description;
    private String raw;
    private String engineer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEq_Id() {
        return eq_Id;
    }

    public void setEq_Id(Integer eq_Id) {
        this.eq_Id = eq_Id;
    }

    public Integer getTm_Id() {
        return tm_Id;
    }

    public void setTm_Id(Integer tm_Id) {
        this.tm_Id = tm_Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
