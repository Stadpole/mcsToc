package com.example.dubbo.provider.service.kafka;


import com.example.dubbo.api.entity.Alarm;
import com.example.dubbo.api.entity.OperationLog;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import com.example.dubbo.provider.dao.OperationLogDao;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class KafkaTeleControlReceiver {
    @Autowired
    private StringToObjectUtil stringToObjectUtil;
    @Resource
    private OperationLogDao operationLogDao;
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    //实际的
 //   @KafkaListener(topics = {"TcCallback"})
    @KafkaListener(topics = {"aa"})
    public void listen(String telecontrolLog) {
        try {
            log.info("遥控执行情况返回信息接收成功==================== telecontrolLog+ " + telecontrolLog);

            //遥控执行情况信息解码
            OperationLog operationLog = stringToObjectUtil.ConvertToTeleControlLog(telecontrolLog);

            if (operationLog.getOperationDetail().equals("发送成功")) {
                /**
                 * 遥控执行情况持久化
                 */
                operationLogDao.insert(operationLog);
            } else {
                /**
                 * 遥控执行情况发送至告警
                 */
                Gson gson=new Gson();
                Alarm alarm=new Alarm();
                alarm.setTime(operationLog.getTime());
                alarm.setWarningDetail(operationLog.getOperationDetail());
                alarm.setEquipmentId(operationLog.getParam().substring(4,6));
                alarm.setType("遥控发令告警");
                alarm.setLatchedStatus("alarm");
                alarm.setAck("no");
                kafkaTemplate.send("Alarm", gson.toJson(alarm));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  //@KafkaListener(topics = {"Tc"})
  @KafkaListener(topics = {"11"})
    public void listenTeleControl(String telecontrol) {
        try {
            log.info("遥控执行发送信息接收成功==================== telecontrol+ " + telecontrol);

            //遥控执行情况信息解码
            OperationLog operationLog = stringToObjectUtil.Convert2TeleControlLog(telecontrol);

            /**
             * 遥控发送情况持久化
             */
            operationLogDao.insert(operationLog);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

