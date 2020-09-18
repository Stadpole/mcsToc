package com.example.dubbo.provider.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stadpole on 2020/5/8 10:37
 */

@Entity(name = "t_threshold")
public class Threshold implements Serializable {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  //门限ID
    private String equipment_id;  //设备ID
    private String equipment_name; //设备名称
    private String station_name;//设备站名
    private String telemetry_name;//遥测点名称
    private Double high_red;  //高红门限
    private Double low_red;  //低红门限
    private Double high_yellow;  //高黄门限
    private Double low_yellow;  //低黄门限
    private String dispersed_status;//离散量遥测状态
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false )
    private Date time;   //时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public Double getHigh_red() {
        return high_red;
    }

    public void setHigh_red(Double high_red) {
        this.high_red = high_red;
    }

    public Double getLow_red() {
        return low_red;
    }

    public void setLow_red(Double low_red) {
        this.low_red = low_red;
    }

    public Double getHigh_yellow() {
        return high_yellow;
    }

    public void setHigh_yellow(Double high_yellow) {
        this.high_yellow = high_yellow;
    }

    public Double getLow_yellow() {
        return low_yellow;
    }

    public void setLow_yellow(Double low_yellow) {
        this.low_yellow = low_yellow;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getTelemetry_name() {
        return telemetry_name;
    }

    public void setTelemetry_name(String telemetry_name) {
        this.telemetry_name = telemetry_name;
    }

    public String getDispersed_status() {
        return dispersed_status;
    }

    public void setDispersed_status(String dispersed_status) {
        this.dispersed_status = dispersed_status;
    }
}
