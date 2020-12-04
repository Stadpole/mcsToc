package com.example.dubbo.provider.service.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class KafkaLinkAlarmReceiverAndProducer {

    @Resource
    private AlarmToDBService alarmToDBService;


   @KafkaListener(topics = {"NetLinkStatus"})
    public void listen(String netLinkAlarm) {
        try {
            log.info("链路告警接收成功==================== telemetries+ " + netLinkAlarm);
            /**
             * 链路告警处理持久化,并发送到kafka
             */
            alarmToDBService.AlarmToDataBase(netLinkAlarm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

