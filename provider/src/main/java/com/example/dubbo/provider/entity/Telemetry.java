package com.example.dubbo.provider.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stadpole on 2020/5/8 10:37
 */

@Entity(name = "t_telemetry_history")
public class Telemetry implements Serializable {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;  //ID
    private Integer equipment_id;  //设备ID
    private String telemetry_name; //遥测名称
    private String engineering_value;  //工程值
    private String unit;  //单位
    private Date time;   //时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getTelemetry_name() {
        return telemetry_name;
    }

    public void setTelemetry_name(String telemetry_name) {
        this.telemetry_name = telemetry_name;
    }

    public String getEngineering_value() {
        return engineering_value;
    }

    public void setEngineering_value(String engineering_value) {
        this.engineering_value = engineering_value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
