package com.example.dubbo.provider.service.kafka;

import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Stadpole on 2020/11/17 10:05
 */
@Component
@Slf4j
public class ThreadStart {

//    @Autowired
//    private TelemetryToDBService telemetryToDB;

    @Autowired
    private TelemetryToMapService telemetryToMapUtil;
    @Autowired
    private AlarmByThresholdService alarmByThreshold;
    @Autowired
    private DispersedAlarmByThresholdService dispersedAlarm;

    @Autowired
    private AlarmToDBService alarmToDBService;
    @PostConstruct
    void startMethod() {
        try {
          //  telemetryToDB.TelemetryCacheToDataBase();
            telemetryToMapUtil.telemetryCacheToMap();
            alarmByThreshold.alarmInCacheByThreshold();
            dispersedAlarm.dispersedAlarmCache();
            alarmToDBService.AlarmCacheToDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
