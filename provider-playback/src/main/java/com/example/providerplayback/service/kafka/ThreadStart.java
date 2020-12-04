package com.example.providerplayback.service.kafka;

import com.example.providerplayback.service.AlarmRecoveryService;
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

//
    @Autowired
    private AlarmRecoveryService alarmRecoveryService;
    @PostConstruct
    void startMethod() {
        try {
         alarmRecoveryService.alarmRecoveryTokafka();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
