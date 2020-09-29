package com.example.dubbo.provider.service.kafka;


import com.example.dubbo.provider.service.AlarmByThresholdService;
import com.example.dubbo.provider.service.DispersedAlarmByThresholdService;
import com.example.dubbo.provider.service.TelemetryToDBService;
import com.example.dubbo.provider.service.TelemetryToMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaTelemetryReceiver {
    @Autowired
    private TelemetryToDBService telemetryToDB;

    @Autowired
    private TelemetryToMapService telemetryToMapUtil;
    @Autowired
    private AlarmByThresholdService alarmByThreshold;
    @Autowired
    private DispersedAlarmByThresholdService dispersedAlarm;

    @KafkaListener(topics = {"Tm"})
   // @KafkaListener(topics = {"33"})
    public void listen(String telemetries) {
        try {
            log.info("遥测接收成功==================== telemetries+ " + telemetries);
            //TODO：遥测持久化,批量存储
            telemetryToDB.TelemetryToDataBase(telemetries);
            //TODO：实时遥测存放在内存表
            telemetryToMapUtil.telemetryToMap(telemetries);
            //TODO:根据门限判断告警
            alarmByThreshold.alarmByThreshold(telemetries);
            //TODO：离散量告警
            dispersedAlarm.dispersedAlarm(telemetries);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

