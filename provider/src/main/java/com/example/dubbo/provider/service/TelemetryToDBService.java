package com.example.dubbo.provider.service;

import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import com.google.common.collect.Queues;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Stadpole on 2020/8/13 11:24
 */
@Component
@Slf4j
public class TelemetryToDBService {

    @Autowired
    private StringToObjectUtil stringToObjectUtil;
    @Autowired
    private TelemetryBatchService telemetryBatchService;
    List<Telemetry> list = Collections.synchronizedList(new ArrayList());
    private static BlockingQueue<String> cache = Queues.newLinkedBlockingQueue(1000);

    @Async
    public void TelemetryToDataBase(String telemetries) {
        try{
            cache.put(telemetries);
            TelemetryCacheToDataBase();
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
    public void TelemetryCacheToDataBase() {
        try{
            Telemetry telemetry = stringToObjectUtil.ConvertToTelemrtry(cache);
            if (list.size() == 10) {
                telemetryBatchService.insertTelemetryBatch(list);
                list.clear();
            } else {
                list.add(telemetry);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
