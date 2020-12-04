package com.example.providerplayback.service.kafka;

import com.example.providerplayback.service.AlarmRecoveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Stadpole on 2020/11/24 10:17
 */
@Component
@Slf4j
public class AlarmReceiver {
    @Resource
    private AlarmRecoveryService alarmRecoveryService;
    @KafkaListener(topics = {"Alarm"})
    public void listenA(String alarms) {
        try {
            alarmRecoveryService.alarmToCache(alarms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
