package com.example.dubbo.provider.service;

import com.example.dubbo.api.entity.Alarm;
import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.api.entity.Threshold;
import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import com.example.dubbo.provider.dao.AlarmDao;
import com.example.dubbo.provider.dao.EquipmentDetailDao;
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
public class AlarmByThresholdService {

    @Autowired
    private StringToObjectUtil stringToObjectUtil;

    @Resource
    private ThresholdDao thresholdDao;

    @Resource
    private EquipmentDetailDao equipmentDetailDao;
    @Resource
    private AlarmUtils alarmUtils;

    @Resource
    private AlarmDao alarmDao;

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();
    private static BlockingQueue<String> cache = Queues.newLinkedBlockingQueue(1000);

    @Async
    public void alarmByThreshold(String telemetries) {
        try{
            cache.put(telemetries);
            alarmInCacheByThreshold();
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
    //TODO：遥测根据门限判断告警
    public String alarmInCacheByThreshold() {
        try {
            Telemetry telemetry = stringToObjectUtil.ConvertToTelemrtry(cache);
            List<Threshold> lists = thresholdDao.findAll();
            if (!lists.isEmpty() && telemetry != null && telemetry.getEquipmentId() != null && telemetry.getEngineeringValue() != null) {

                for (Threshold threshold : lists) {
//                    log.info("门限遥测id："+threshold.getEquipmentId());
//                    log.info("遥测id："+telemetry.getEquipmentId()+"比较结果："+threshold.getEquipmentId().equals(telemetry.getEquipmentId()));
//                    log.info("门限遥测名称："+threshold.getTelemetryName());
//                    log.info("遥测名称："+telemetry.getTelemetryName()+"比较结果："+telemetry.getTelemetryName().equals(threshold.getTelemetryName()));

                    if (threshold.getEquipmentId().equals(telemetry.getEquipmentId())&&telemetry.getTelemetryName().equals(threshold.getTelemetryName())) {

                        Alarm alarm=new Alarm();
                        String value=telemetry.getEngineeringValue();
                        Double temp = Double.valueOf(value);
                        //TODO:高红
                        if (temp >= threshold.getHighRed()) {
                            //TODO：生成告警
                           alarm=alarmUtils.alarmUtils(threshold,telemetry,"高红告警", threshold.getHighRed());
                          //TODO:告警信息持久化
                            alarmDao.insert(alarm);
                            //TODO：告警实时推送
                            kafkaTemplate.send("Alarm", gson.toJson(alarm));


                        }//TODO:高黄
                        else if (temp >= threshold.getHighYellow()&&temp<threshold.getHighRed()) {
                            //TODO：生成告警
                            alarm=alarmUtils.alarmUtils(threshold,telemetry,"高黄告警", threshold.getHighYellow());
                            //TODO:告警信息持久化
                            alarmDao.insert(alarm);
                            //TODO：告警实时推送
                            kafkaTemplate.send("Alarm", gson.toJson(alarm));

                        }
                        //TODO:低红
                        else if (temp <= threshold.getLowRed()) {

                            //TODO：生成告警
                            alarm=alarmUtils.alarmUtils(threshold,telemetry,"低红告警", threshold.getLowRed());
                            //TODO:告警信息持久化
                            alarmDao.insert(alarm);
                            //TODO：告警实时推送
                            kafkaTemplate.send("Alarm", gson.toJson(alarm));

                        }
                        //TODO:低黄
                        else if (temp <= threshold.getLowYellow() && temp > threshold.getLowRed()) {
                            //TODO：生成告警
                            alarm=alarmUtils.alarmUtils(threshold,telemetry,"低黄告警", threshold.getLowYellow());
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
