package com.example.dubbo.provider.service;

import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import com.google.common.collect.Queues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stadpole on 2020/8/13 11:24
 */
@Component
@Slf4j
public class TelemetryToMapService {
    @Autowired
    private StringToObjectUtil stringToObjectUtil;
    private static ConcurrentHashMap<String, Telemetry> map = new ConcurrentHashMap<String, Telemetry>();
    //private static HashMap<String, Telemetry> map = new HashMap<>();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static BlockingQueue<String> cache = Queues.newLinkedBlockingQueue(1000);

   @Async
    public void telemetryToMap(String telemetries) {
        try {
            cache.put(telemetries);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Async
    public void telemetryCacheToMap() {
        while (true) {
            try {
                String take = cache.take();
                Telemetry telemetry = stringToObjectUtil.ConvertToTelemrtry(take);
                String key = telemetry.getEquipmentId() + telemetry.getTelemetryName();
                map.put(key, telemetry);
                log.info("遥测存入内存" + df.format(new Date()));

            } catch (Exception e) {
                map.put("fail", null);
                e.printStackTrace();
            }
        }

    }

    public ConcurrentHashMap<String, Telemetry> telemetryReal() {
//        log.info("map.size" + map.size());
        return map;
    }
}
