package com.example.dubbo.provider.service;

import com.example.dubbo.provider.common.utils.StringToTelemetryUtil;
import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.service.TelemetryBatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stadpole on 2020/8/13 11:24
 */
@Component
@Slf4j
public class TelemetryToMapService {
    @Autowired
    private StringToTelemetryUtil stringToTelemetryUtil;
    HashMap<String, Telemetry> map = new HashMap<>();

    public HashMap<String, Telemetry> telemetryToMap(String telemetries) {
        try {
            Telemetry telemetry = stringToTelemetryUtil.ConvertToTelemrtry(telemetries);
//            if (map.size() == 0) {
//                map.put(telemetry.getEquipment_id(), telemetry);
//            } else {
//                for (Map.Entry<Integer, Telemetry> entry : map.entrySet()) {
//                    if (telemetry.getEquipment_id() == entry.getKey()) {
//                        map.replace(telemetry.getEquipment_id(), telemetry);
//                    } else {
//                        map.put(telemetry.getEquipment_id(), telemetry);
//                    }
//                }
//            }
            String key= telemetry.getEquipment_id() +telemetry.getTelemetry_name();
            map.put(key, telemetry);
        } catch (Exception e) {
            map.put("fail",null);
            e.printStackTrace();
        }
//        for (Map.Entry<Integer, Telemetry> entry : map.entrySet()) {
//            log.info("map内容:" + entry.getValue().getEquipment_id() + entry.getValue().getTelemetry_name());
//        }
        return map;
    }
    public HashMap<String, Telemetry> telemetryReal() {
        return map;
    }

}
