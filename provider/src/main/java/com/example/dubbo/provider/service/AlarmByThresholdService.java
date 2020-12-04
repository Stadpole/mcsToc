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
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    public  void alarmByThreshold(String telemetries) {
        try {
            cache.put(telemetries);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    //TODO：遥测根据门限判断告警

    @Async
    public void alarmInCacheByThreshold() {
        while (true) {
            try {
                String take = cache.take();
                Telemetry telemetry = stringToObjectUtil.ConvertToTelemrtry(take);
                List<Threshold> lists = thresholdDao.findExceptDispersed_status();
                if (!lists.isEmpty() && telemetry != null && telemetry.getEquipmentId() != null && telemetry.getEngineeringValue() != null) {
                    for (Threshold threshold : lists) {

                        if (threshold.getEquipmentId().equals(telemetry.getEquipmentId()) && telemetry.getTelemetryName().equals(threshold.getTelemetryName())) {

                            Alarm alarm = new Alarm();
                            String value = telemetry.getEngineeringValue();
                            Double temp = Double.valueOf(value);
                            //TODO:高红
                            if (temp >= threshold.getHighRed()) {
                                //TODO：生成告警
                                alarm = alarmUtils.alarmUtils(threshold, telemetry, "高红告警", threshold.getHighRed());
                                //TODO:告警信息持久化
                                alarmDao.insert(alarm);
                                //TODO：告警实时推送
                                kafkaTemplate.send("Alarm", gson.toJson(alarm));
                                /**
                                 * 保存实时告警信息到内存Map,key为告警信息，value 为记录的时间戳
                                 * 如果value与当前时间超过15秒证明告警恢复
                                 * 推送恢复信息到kafka队列                             *
                                 */
                        //        alarmUtils.alarmJudgeRecovery(alarm);


                            }//TODO:高黄
                            else if (temp >= threshold.getHighYellow() && temp < threshold.getHighRed()) {
                                //TODO：生成告警
                                alarm = alarmUtils.alarmUtils(threshold, telemetry, "高黄告警", threshold.getHighYellow());
                                //TODO:告警信息持久化
                                alarmDao.insert(alarm);
                                //TODO：告警实时推送
                                kafkaTemplate.send("Alarm", gson.toJson(alarm));
                                /**
                                 * 保存实时告警信息到内存Map,key为告警信息，value 为记录的时间戳
                                 * 如果value与当前时间超过15秒证明告警恢复
                                 * 推送恢复信息到kafka队列                             *
                                 */
                             //   alarmUtils.alarmJudgeRecovery(alarm);

                            }
                            //TODO:低红
                            else if (temp <= threshold.getLowRed()) {

                                //TODO：生成告警
                                alarm = alarmUtils.alarmUtils(threshold, telemetry, "低红告警", threshold.getLowRed());
                                //TODO:告警信息持久化
                                alarmDao.insert(alarm);
                                //TODO：告警实时推送
                                kafkaTemplate.send("Alarm", gson.toJson(alarm));
                                /**
                                 * 保存实时告警信息到内存Map,key为告警信息，value 为记录的时间戳
                                 * 如果value与当前时间超过15秒证明告警恢复
                                 * 推送恢复信息到kafka队列                             *
                                 */
                            //    alarmUtils.alarmJudgeRecovery(alarm);

                            }
                            //TODO:低黄
                            else if (temp <= threshold.getLowYellow() && temp > threshold.getLowRed()) {
                                //TODO：生成告警
                                alarm = alarmUtils.alarmUtils(threshold, telemetry, "低黄告警", threshold.getLowYellow());
                                //TODO:告警信息持久化
                                alarmDao.insert(alarm);
                                /**
                                 * 告警实时推送
                                 */
                                kafkaTemplate.send("Alarm", gson.toJson(alarm));

                                /**
                                 * 保存实时告警信息到内存Map,key为告警信息，value 为记录的时间戳
                                 * 如果value与当前时间超过15秒证明告警恢复
                                 * 推送恢复信息到kafka队列                             *
                                 */
                             //   alarmUtils.alarmJudgeRecovery(alarm);


                            }

                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
