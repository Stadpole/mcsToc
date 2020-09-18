package com.example.dubbo.provider.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Stadpole on 2020/5/8 10:37
 */

@Entity(name = "t_equipment_detail")
public class EquipmentDetail implements Serializable {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String equipment_id;
    private String station_name;
    private String equipment_name;
    private String driver_name;

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }
}
