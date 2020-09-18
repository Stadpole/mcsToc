package com.example.dubbo.provider.service;

import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.common.utils.StringToTelemetryUtil;
import com.example.dubbo.provider.entity.Alarm;
import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.entity.Threshold;
import com.example.dubbo.provider.mapper.EquipmentDetailMapper;
import com.example.dubbo.provider.mapper.ThresholdMapper;
import com.example.dubbo.provider.repository.AlarmRepository;
import com.example.dubbo.provider.repository.ThresholdRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stadpole on 2020/8/13 13:51
 */
@Component
@Slf4j
public class AlarmByThresholdService {

    @Autowired
    private StringToTelemetryUtil stringToTelemetryUtil;
    @Autowired
    private ThresholdRepository thresholdRepository;

    @Autowired(required = false)
    private EquipmentDetailMapper equipmentDetailMapper;
    @Autowired
    private AlarmUtils alarmUtils;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //TODO：遥测根据门限判断告警
    public String alarmByThreshold(String telemetries) {
        try {
            Telemetry telemetry = stringToTelemetryUtil.ConvertToTelemrtry(telemetries);
            List<Threshold> lists = thresholdRepository.findAll();
            if (!lists.isEmpty() && telemetry != null && telemetry.getEquipment_id() != null && telemetry.getEngineering_value() != null) {

                for (Threshold threshold : lists) {
                    log.info("门限遥测id："+threshold.getEquipment_id());
                    log.info("遥测id："+telemetry.getEquipment_id()+"比较结果："+threshold.getEquipment_id().equals(telemetry.getEquipment_id()));
                    log.info("门限遥测名称："+threshold.getTelemetry_name());
                    log.info("遥测名称："+telemetry.getTelemetry_name()+"比较结果："+telemetry.getTelemetry_name().equals(threshold.getTelemetry_name()));

                    if (threshold.getEquipment_id().equals(telemetry.getEquipment_id())&&telemetry.getTelemetry_name().equals(threshold.getTelemetry_name())) {

                        Alarm alarm=new Alarm();
                        String value=telemetry.getEngineering_value();
                        Double temp = Double.valueOf(value);
                        //TODO:高红
                        if (temp >= threshold.getHigh_red()) {
                            //TODO：生成告警
                           alarm=alarmUtils.alarmUtils(threshold,telemetry,"高红告警", threshold.getHigh_red());
                          //TODO:告警信息持久化
                            alarmRepository.save(alarm);
                            //TODO：告警实时推送
                            kafkaTemplate.send("Alarm", gson.toJson(alarm));


                        }//TODO:高黄
                        else if (temp >= threshold.getHigh_yellow()&&temp<threshold.getHigh_red()) {
                            //TODO：生成告警
                            alarm=alarmUtils.alarmUtils(threshold,telemetry,"高黄告警", threshold.getHigh_yellow());
                            //TODO:告警信息持久化
                            alarmRepository.save(alarm);
                            //TODO：告警实时推送
                            kafkaTemplate.send("Alarm", gson.toJson(alarm));

                        }
                        //TODO:低红
                        else if (temp <= threshold.getLow_red()) {

                            //TODO：生成告警
                            alarm=alarmUtils.alarmUtils(threshold,telemetry,"低红告警", threshold.getLow_red());
                            //TODO:告警信息持久化
                            alarmRepository.save(alarm);
                            //TODO：告警实时推送
                            kafkaTemplate.send("Alarm", gson.toJson(alarm));

                        }
                        //TODO:低黄
                        else if (temp <= threshold.getLow_yellow() && temp > threshold.getLow_red()) {
                            //TODO：生成告警
                            alarm=alarmUtils.alarmUtils(threshold,telemetry,"低黄告警", threshold.getLow_yellow());
                            //TODO:告警信息持久化
                            alarmRepository.save(alarm);
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
