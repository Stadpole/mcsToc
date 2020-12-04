package com.example.providerplayback.service;

import com.example.dubbo.api.entity.AlarmRecovery;
import com.google.common.collect.Queues;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stadpole on 2020/11/24 10:18
 */
@Component
@Slf4j
public class AlarmRecoveryService {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static BlockingQueue<String> cache = Queues.newLinkedBlockingQueue(1000);
    private Gson gson = new GsonBuilder().create();
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;
    private static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();
    static boolean b = true;

    @Async
    public void alarmToCache(String alarms) {
        try {
            cache.put(alarms);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Async
    public void alarmRecoveryTokafka() {
        while (true) {
            try {
                String take = cache.take();
                AlarmRecovery alarm = gson.fromJson(take, AlarmRecovery.class);
                alarm.setType("告警恢复");
                alarm.setTime(0L);
                alarm.setId(0);
                alarm.setDateTime(null);
                alarm.setWarningDetail(null);
                String str = gson.toJson(alarm);
                b=false;
                map.put(str, System.currentTimeMillis());
                b=true;

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    @Scheduled(initialDelay = 10, fixedDelay = 100)
    public void sendAlarmRecover() {
        if (map.size() >0) {
            Long isRecveryTime = System.currentTimeMillis();
            for (Iterator<Map.Entry<String, Long>> it = map.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Long> item = it.next();
                Long timeDiff = Math.abs(isRecveryTime - item.getValue()) / 1000;
                if (timeDiff > 10) {
                    kafkaTemplate.send("recovery", item.getKey());
                    if (b==true) {
                        it.remove();
                    }
                }
            }
        }

    }
}