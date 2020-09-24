package com.example.dubbo.provider.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.request.AlarmRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.AlarmService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Stadpole on 2020/8/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@Service(version = "1.0.0")
@Slf4j
public class AlarmServiceImpl implements AlarmService {


    @Override
    public BaseResponse pageAlarm(Integer page, Integer size, String equipment_id, String startTime, String endTime) {
        return null;
    }

    @Override
    public BaseResponse updateAlarm(AlarmRequest alarmRequest) {
        return null;
    }
}