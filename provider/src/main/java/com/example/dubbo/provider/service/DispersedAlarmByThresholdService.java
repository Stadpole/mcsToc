package com.example.dubbo.provider.service;

import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.common.utils.StringToTelemetryUtil;
import com.example.dubbo.provider.entity.Alarm;
import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.entity.Threshold;
import com.example.dubbo.provider.mapper.ThresholdMapper;
import com.example.dubbo.provider.repository.AlarmRepository;
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
public class DispersedAlarmByThresholdService {

    @Autowired
    private StringToTelemetryUtil stringToTelemetryUtil;

    @Autowired
    private AlarmUtils alarmUtils;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired(required = false)
    private ThresholdMapper thresholdMapper;

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //TODO：遥测离散告警判断
    public String dispersedAlarm(String telemetries) {
        try {
            Telemetry telemetry = stringToTelemetryUtil.ConvertToTelemrtry(telemetries);
            List<Threshold> lists = thresholdMapper.findByDispersed_status();
            if (!lists.isEmpty() && telemetry != null && telemetry.getEquipment_id() != null && telemetry.getEngineering_value() != null) {

                for (Threshold threshold : lists) {
                    log.info("门限遥测id："+threshold.getEquipment_id());
                    log.info("遥测id："+telemetry.getEquipment_id()+"比较结果："+threshold.getEquipment_id().equals(telemetry.getEquipment_id()));
                    log.info("门限遥测名称："+threshold.getTelemetry_name());
                    log.info("遥测名称："+telemetry.getTelemetry_name()+"比较结果："+telemetry.getTelemetry_name().equals(threshold.getTelemetry_name()));

                    if (threshold.getEquipment_id().equals(telemetry.getEquipment_id())&&telemetry.getTelemetry_name().equals(threshold.getTelemetry_name())) {

                        Alarm alarm=new Alarm();
                        String value=telemetry.getEngineering_value();

                        //TODO:告警判断
                        if (value.equals(threshold.getDispersed_status())) {
                            //TODO：生成告警
                           alarm=alarmUtils.dispersedAlarmUtils(threshold,telemetry,"状态告警+门限值：", threshold.getDispersed_status());
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
