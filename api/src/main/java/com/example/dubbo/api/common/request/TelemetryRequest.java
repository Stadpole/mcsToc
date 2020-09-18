package com.example.dubbo.api.common.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stadpole on 2020/5/8 10:37
 */
@Data
@ToString
public class TelemetryRequest implements Serializable {
    private Integer id;  //ID
    private String equipment_id;  //设备ID
    private String telemetry_name; //遥测名称
    private String engineering_value;  //工程值
    private String unit;  //单位
    private Date time;   //时间
}
