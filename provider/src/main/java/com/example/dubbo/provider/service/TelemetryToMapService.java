package com.example.dubbo.provider.service;

import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Stadpole on 2020/8/13 11:24
 */
@Component
@Slf4j
public class TelemetryToMapService {
    @Autowired
    private StringToObjectUtil stringToObjectUtil;
    HashMap<String, Telemetry> map = new HashMap<>();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public HashMap<String, Telemetry> telemetryToMap(String telemetries) {
        try {
            log.info("遥测接收成功"+df.format(new Date()));
            Telemetry telemetry = stringToObjectUtil.ConvertToTelemrtry(telemetries);
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
            String key= telemetry.getEquipmentId() +telemetry.getTelemetryName();
            map.put(key, telemetry);
            log.info("遥测处理成功"+df.format(new Date()));
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
