package com.example.dubbo.provider.service.kafka;

import com.example.dubbo.api.entity.Alarm;
import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.dao.AlarmDao;
import com.google.common.collect.Queues;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Stadpole on 2020/8/13 11:24
 */
@Component
@Slf4j
@Async
public class AlarmToDBService {
    @Autowired
    private AlarmUtils alarmUtils;

    @Resource
    private AlarmDao alarmDao;
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();
    static List<Alarm>  list = Collections.synchronizedList(new ArrayList());
    private static BlockingQueue<String> cache = Queues.newLinkedBlockingQueue(1000);


    public void AlarmToDataBase(String netLinkAlarm) {
        try {
            cache.put(netLinkAlarm);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    public void AlarmCacheToDataBase() {
        while (true) {
            try {
                String take = cache.take();
                Alarm alarm = alarmUtils.netLinkalarmUtils(take);
                alarmDao.insert(alarm);
                alarm.setTelemetryName("link");
                kafkaTemplate.send("Alarm", gson.toJson(alarm));
                /**
                 * 保存实时告警信息到内存Map,key为告警信息，value 为记录的时间戳
                 * 如果value与当前时间超过15秒证明告警恢复
                 * 推送恢复信息到kafka队列                             *
                 */
             //   alarmUtils.alarmJudgeRecovery(alarm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
