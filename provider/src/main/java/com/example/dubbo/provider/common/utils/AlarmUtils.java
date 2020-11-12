package com.example.dubbo.provider.common.utils;

import com.example.dubbo.api.entity.Alarm;
import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.api.entity.Threshold;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

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
    private HashMap<String, Long> map = new HashMap<>();

    /**
     * 遥测告警实例化
     */
    public Alarm alarmUtils(Threshold threshold, Telemetry telemetry, String alarmName, Double alarmThreshold) {
        //TODO：生成告警
        Alarm alarm = new Alarm();
        try {
            log.info("门限告警工具类启用");
            //TODO:告警详情：站名+设备名+遥测点名称+当前遥测值+门限范围+告警类型（高红/高黄/低红/低黄）
            alarm.setWarningDetail(threshold.getStationName() + threshold.getEquipmentName() + telemetry.getTelemetryName() + "当前遥测值：" +
                    telemetry.getEngineeringValue() + alarmName + alarmThreshold);
            alarm.setEquipmentId(telemetry.getEquipmentId());
            alarm.setType("遥测门限告警");
            alarm.setDateTime(format.format(telemetry.getTime()));
            alarm.setLatchedStatus("alarm");
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
    public Alarm netLinkalarmUtils(BlockingQueue<String> cache) {
        String netLinkAlarm = cache.poll();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Alarm alarm = new Alarm();
        try {
            log.info("链路告警工具类启用");
            //TODO:告警信息解析
            String[] strings = netLinkAlarm.split(";");

            // alarm.setTime(format.parse(strings[0]));
            alarm.setDateTime(strings[0]);
            alarm.setEquipmentId(strings[1]);
            alarm.setWarningDetail(strings[2]);
            //  alarm.setTime(format.parse(strings[3]));
            alarm.setType("链路告警");
            alarm.setLatchedStatus("alarm");
            alarm.setOpen("Yes");
            alarm.setAck("NO");
            kafkaTemplate.send("ALarm", gson.toJson(alarm));
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
            alarm.setWarningDetail(threshold.getStationName() + threshold.getEquipmentName() + telemetry.getTelemetryName());
            alarm.setEquipmentId(telemetry.getEquipmentId());
            alarm.setType("离散门限告警");
            //alarm.setTime(telemetry.getTime());
            alarm.setDateTime(format.format(telemetry.getTime()));
            alarm.setLatchedStatus("alarm");
            alarm.setOpen("Yes");
            alarm.setAck("NO");
            return alarm;
        } catch (Exception e) {
            log.error("遥测离散告警工具类异常：", e.fillInStackTrace());

        }
        return alarm;
    }

    /**
     * 保存实时告警信息到内存Map,key为告警信息，value 为记录的时间戳
     * 如果value与当前时间超过15秒证明告警恢复
     * 推送恢复信息到kafka队列                             *
     */
    public void alarmJudgeRecovery(Alarm alarm) {
        alarm.setId(0);
        alarm.setTime(null);
        String str = gson.toJson(alarm);
        map.put(str, System.currentTimeMillis());

    }

    //定时检查告警有没有恢复
    @Scheduled(initialDelay = 10, fixedDelay = 15000)
    public void isRecovery() {
        Long isRecveryTime = System.currentTimeMillis();
        if (map.size() >= 0) {
            for (Iterator<Map.Entry<String, Long>> it = map.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Long> item = it.next();
                if ((isRecveryTime - item.getValue()) / 1000 > 15) {
                    kafkaTemplate.send("recovery", gson.toJson(item.getKey()));
                    it.remove();
                }
            }
        }
    }
}
