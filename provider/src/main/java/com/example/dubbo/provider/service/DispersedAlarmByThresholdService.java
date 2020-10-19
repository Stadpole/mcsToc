package com.example.dubbo.provider.service;

import com.example.dubbo.api.entity.Alarm;
import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.api.entity.Threshold;
import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import com.example.dubbo.provider.dao.AlarmDao;
import com.example.dubbo.provider.dao.ThresholdDao;
import com.google.common.collect.Queues;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Stadpole on 2020/8/13 13:51
 */
@Component
@Slf4j
public class DispersedAlarmByThresholdService {

    @Autowired
    private StringToObjectUtil stringToObjectUtil;

    @Autowired
    private AlarmUtils alarmUtils;

    @Resource
    private AlarmDao alarmDao;

    @Resource
    private ThresholdDao thresholdDao;

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();
    private static BlockingQueue<String> cache = Queues.newLinkedBlockingQueue(1000);

    @Async
    public void dispersedAlarm(String telemetries) {
        try{
            cache.put(telemetries);
            dispersedAlarmCache();
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
    //TODO：遥测离散告警判断
    public String dispersedAlarmCache() {
        try {
            Telemetry telemetry = stringToObjectUtil.ConvertToTelemrtry(cache);
            List<Threshold> lists = thresholdDao.findByDispersed_status();
            if (!lists.isEmpty() && telemetry != null && telemetry.getEquipmentId() != null && telemetry.getEngineeringValue() != null) {

                for (Threshold threshold : lists) {
                    log.info("门限遥测id："+threshold.getEquipmentId());
                    log.info("遥测id："+telemetry.getEquipmentId()+"比较结果："+threshold.getEquipmentId().equals(telemetry.getEquipmentId()));
                    log.info("门限遥测名称："+threshold.getTelemetryName());
                    log.info("遥测名称："+telemetry.getTelemetryName()+"比较结果："+telemetry.getTelemetryName().equals(threshold.getTelemetryName()));

                    if (threshold.getEquipmentId().equals(telemetry.getEquipmentId())&&telemetry.getTelemetryName().equals(threshold.getTelemetryName())) {

                        Alarm alarm=new Alarm();
                        String value=telemetry.getEngineeringValue();

                        //TODO:告警判断
                        if (value.equals(threshold.getDispersedStatus())) {
                            //TODO：生成告警
                           alarm=alarmUtils.dispersedAlarmUtils(threshold,telemetry,"状态告警+门限值：", threshold.getDispersedStatus());
                          //TODO:告警信息持久化
                            alarmDao.insert(alarm);
                            //TODO：告警实时推送
                            kafkaTemplate.send("Alarm", gson.toJson(alarm));

                        }

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
