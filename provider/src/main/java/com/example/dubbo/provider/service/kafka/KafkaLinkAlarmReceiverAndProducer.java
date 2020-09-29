package com.example.dubbo.provider.service.kafka;


import com.example.dubbo.api.entity.Alarm;
import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.dao.AlarmDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class KafkaLinkAlarmReceiverAndProducer {
    @Resource
    private AlarmDao alarmDao;
    @Autowired
    private AlarmUtils alarmUtils;

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = {"NetLinkStatus"})
    public void listen(String netLinkAlarm) {
        try {
            log.info("链路告警接收成功==================== telemetries+ " + netLinkAlarm);
            //TODO：链路告警解析
            Alarm alarm = alarmUtils.netLinkalarmUtils(netLinkAlarm);

            //TODO：链路告警持久化
            alarmDao.insert(alarm);
            //TODO:链路告警推送
            kafkaTemplate.send("Alarm", gson.toJson(alarm));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

