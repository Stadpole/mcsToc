package com.example.dubbo.provider.kafka;


import com.example.dubbo.provider.service.TelemetryToDBService;
import com.example.dubbo.provider.service.TelemetryToMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaReceiver {
    @Autowired
    private TelemetryToDBService telemetryToDB;

    @Autowired
    private TelemetryToMapService telemetryToMapUtil;

    @KafkaListener(topics = {"testTocpic"})
    public void listen(String telemetries) {
        try {
            log.info("遥测接收成功==================== telemetries+ " + telemetries);
            //TODO：遥测持久化,批量存储
            telemetryToDB.TelemetryToDataBase(telemetries);
            //TODO：实时遥测存放在内存表
            telemetryToMapUtil.telemetryToMap(telemetries);
            //TODO:根据门限判断告警


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

