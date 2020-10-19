package com.example.dubbo.provider.service.kafka;

import com.example.dubbo.api.entity.Alarm;
import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import com.example.dubbo.provider.dao.AlarmDao;
import com.example.dubbo.provider.service.TelemetryBatchService;
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
public class AlarmToDBService {
    @Autowired
    private AlarmUtils alarmUtils;

    @Resource
    private AlarmDao alarmDao;
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();
    List<Alarm> list = Collections.synchronizedList(new ArrayList());
    private static BlockingQueue<String> cache = Queues.newLinkedBlockingQueue(1000);

    @Async
    public void AlarmToDataBase(String netLinkAlarm) {
        try{
            cache.put(netLinkAlarm);
            AlarmCacheToDataBase();
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
    public void AlarmCacheToDataBase() {
        try{
            Alarm alarm = alarmUtils.netLinkalarmUtils(cache);
            alarmDao.insert(alarm);
            kafkaTemplate.send("Alarm", gson.toJson(alarm));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
