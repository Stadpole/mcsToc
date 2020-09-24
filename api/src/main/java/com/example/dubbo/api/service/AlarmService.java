package com.example.dubbo.api.service;

import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Alarm;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
//@Path("alarm")
public interface AlarmService {

    //TODO：分页查询告警日志
//    @Path("1")
    public BaseResponse pageAlarm(Integer page, Integer size, String equipment_id, String startTime,String endTime);


    //TODO：更新告警状态
//    @Path("4")
    public BaseResponse updateAlarm(Alarm alarmRequest);
}