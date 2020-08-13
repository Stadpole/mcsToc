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
public class ThresholdRequest implements Serializable {
    private Integer id;  //门限ID
    private Integer equipment_id;  //设备ID
    private String equipment_name; //设备名称
    private Double high_red;  //高红门限
    private Double low_red;  //低红门限
    private Double high_yellow;  //高黄门限
    private Double low_yellow;  //低黄门限
    private Date time;   //时间
}
