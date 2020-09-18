package com.example.dubbo.provider.service.kafka;


import com.example.dubbo.provider.common.utils.AlarmUtils;
import com.example.dubbo.provider.entity.Alarm;
import com.example.dubbo.provider.repository.AlarmRepository;
import com.example.dubbo.provider.service.AlarmByThresholdService;
import com.example.dubbo.provider.service.TelemetryToDBService;
import com.example.dubbo.provider.service.TelemetryToMapService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaLinkAlarmReceiverAndProducer {
    @Autowired
    private AlarmRepository alarmRepository;
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
            alarmRepository.save(alarm);
            //TODO:链路告警推送
            kafkaTemplate.send("Alarm", gson.toJson(alarm));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

