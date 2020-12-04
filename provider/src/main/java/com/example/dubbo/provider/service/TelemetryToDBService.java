package com.example.dubbo.provider.service;

import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import com.google.common.collect.Queues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stadpole on 2020/8/13 11:24
 */
@Component
@Slf4j
public class TelemetryToDBService {
    @Resource
    private TelemetryToMapService telemetryToMapService;
    @Resource
    private TelemetryBatchService telemetryBatchService;

    private static ConcurrentHashMap<String, Telemetry> map = new ConcurrentHashMap<String, Telemetry>();
    static List<Telemetry> list = Collections.synchronizedList(new ArrayList());

    @Scheduled(initialDelay = 10, fixedDelay = 120000)
    public void TelemetryCacheToDataBase(){
        map=telemetryToMapService.telemetryReal();
        Iterator<Map.Entry<String, Telemetry>> it = map.entrySet().iterator();
        while (it.hasNext()) {
              Map.Entry<String, Telemetry> entry = it.next();
            list.add(entry.getValue());
           }
        if(list.size()>0) {
            telemetryBatchService.insertTelemetryBatch(list);
            log.info("遥测存入数据库");
            list.clear();
        }

    }
//    @Autowired
//    private StringToObjectUtil stringToObjectUtil;
//    @Autowired
//    private TelemetryBatchService telemetryBatchService;
//    static List<Telemetry> list = Collections.synchronizedList(new ArrayList());
//   private static BlockingQueue<String> cache = Queues.newLinkedBlockingQueue(1000);


//    @Async
//    public  void telemetryToDataBase(String telemetries) {
//        try {
//            cache.put(telemetries);
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//
//    }
//
//    @Async
//    public void TelemetryCacheToDataBase() {
//       while (true) {
//            try {
//                String take = cache.take();
//                Telemetry telemetry = stringToObjectUtil.ConvertToTelemrtry(take);
//                if (list.size() == 10000) {
//                    telemetryBatchService.insertTelemetryBatch(list);
//                    log.info("遥测存入数据库");
//                    list.clear();
//                } else {
//                    list.add(telemetry);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}
