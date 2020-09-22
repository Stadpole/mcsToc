package com.example.dubbo.provider.common.utils;

import com.example.dubbo.provider.entity.Alarm;
import com.example.dubbo.provider.entity.EquipmentDetail;
import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.entity.Threshold;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stadpole on 2020/8/18 14:03
 */
@Component
@Slf4j
public class AlarmUtils {
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    Date current = new Date(System.currentTimeMillis());

    /**
     * 遥测告警实例化
     */
    public Alarm alarmUtils(Threshold threshold, Telemetry telemetry, String alarmName, Double alarmThreshold) {
        //TODO：生成告警
        Alarm alarm = new Alarm();
        try {
            log.info("门限告警工具类启用");

            //TODO:告警详情：站名+设备名+遥测点名称+当前遥测值+门限范围+告警类型（高红/高黄/低红/低黄）
            alarm.setWarning_detail(threshold.getStation_name() + threshold.getEquipment_name() + telemetry.getTelemetry_name() + "当前遥测值：" +
                    telemetry.getEngineering_value() + alarmName + alarmThreshold);
            alarm.setEquipment_id(telemetry.getEquipment_id());
            alarm.setType("遥测门限告警");
            alarm.setTime(telemetry.getTime());
            alarm.setLatched_status("alarm");
            alarm.setOpen("Yes");
            alarm.setAck("NO");
            return alarm;
        } catch (Exception e) {
            log.error("门限告警工具类异常：", e.fillInStackTrace());

        }
        return alarm;
    }

    /**
     * 链路告警实例化,实时发布至kafka
     */
    public Alarm netLinkalarmUtils(String netLinkAlarm) {

        DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Alarm alarm = new Alarm();
        try {
            log.info("链路告警工具类启用");
            //TODO:告警信息解析
            String[] strings = netLinkAlarm.split(";");
            alarm.setTime(format.parse(strings[0]));
            alarm.setEquipment_id(strings[1]);
            alarm.setWarning_detail(strings[2]);

          //  alarm.setTime(format.parse(strings[3]));
            alarm.setType("链路告警");
            alarm.setLatched_status("alarm");
            alarm.setOpen("Yes");
            alarm.setAck("NO");
            kafkaTemplate.send("ALarm",gson.toJson(alarm));
            return alarm;
        } catch (Exception e) {
            log.error("链路告警工具类异常：", e.fillInStackTrace());
        }

        return alarm;
    }
    /**
     * 遥测离散告警实例化
     */
    public Alarm dispersedAlarmUtils(Threshold threshold, Telemetry telemetry, String alarmName, String alarmThreshold) {
        //TODO：生成告警
        Alarm alarm = new Alarm();
        try {
            log.info("遥测离散告警工具类启用");

            //TODO:告警详情
            alarm.setWarning_detail(threshold.getStation_name() + threshold.getEquipment_name() + telemetry.getTelemetry_name() + "当前遥测值：" +
                    telemetry.getEngineering_value() + alarmName + alarmThreshold);
            alarm.setEquipment_id(telemetry.getEquipment_id());
            alarm.setType("遥测门限告警");
            alarm.setTime(telemetry.getTime());
            alarm.setLatched_status("alarm");
            alarm.setOpen("Yes");
            alarm.setAck("NO");
            return alarm;
        } catch (Exception e) {
            log.error("遥测离散告警工具类异常：", e.fillInStackTrace());

        }
        return alarm;
    }
}
