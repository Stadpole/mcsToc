package com.example.dubbo.provider.service.kafka;


import com.example.dubbo.provider.service.AlarmByThresholdService;
import com.example.dubbo.provider.service.DispersedAlarmByThresholdService;
import com.example.dubbo.provider.service.TelemetryToDBService;
import com.example.dubbo.provider.service.TelemetryToMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class KafkaTelemetryReceiver {
//    @Resource
//    private TelemetryToDBService telemetryToDBService;
    @Resource
    private TelemetryToMapService telemetryToMapService;
    @Resource
    private AlarmByThresholdService alarmByThresholdService;
    @Resource
    private DispersedAlarmByThresholdService dispersedAlarmByThresholdService;

    @KafkaListener(topics = {"Tm"})
    // @KafkaListener(topics = {"33"})
    public void listen(String telemetries) {
        try {
            /**
             * 遥测持久化,批量存储
             * */
            //telemetryToDBService.telemetryToDataBase(telemetries);
            /**
             * 实时遥测存放在内存表
             */
            telemetryToMapService.telemetryToMap(telemetries);
            /**
             * 根据门限判断告警
             */
            alarmByThresholdService.alarmByThreshold(telemetries);
            /**
             * 离散量告警
             */
            dispersedAlarmByThresholdService.dispersedAlarm(telemetries);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



