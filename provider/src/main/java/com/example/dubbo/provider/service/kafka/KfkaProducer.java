package com.example.dubbo.provider.service.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class KfkaProducer {

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
//        for(int i=0;i<10;i++){
//            Message message = new Message();
//            message.setId(System.currentTimeMillis());
//            message.setMsg(UUID.randomUUID().toString()+ "---" +i);
//            message.setSentTime(new Date());
//            logger.info("发送消息 ----->>>>>  message = {}", gson.toJson(message));
//            kafkaTemplate.send("testTocpic", gson.toJson(message));
//        }
        String[] ss = {"010101", "010102", "010103", "010104", "010105", "010106", "010107", "010108", "010109", "010110"};

        // kafkaTemplate.send("testTocpic", ss[random]+"_俯仰方位角转动_无转动_无_20200811:09:17:59");
        Date date = new Date();//获取当前的日期


        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd:HH:mm:ss");//设置日期格式
        String str = df.format(date);//获取String类型的时间

        //    kafkaTemplate.send("33", "2020-11-05 11:16:32;010101;TX告警;" +11.000 + ";无");

        for (int i = 0; i < 10; i++) {
            double r = Math.random() * 10;
            BigDecimal b = new BigDecimal(r);
            double random = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
//            try {
//                Thread.sleep(3000);
//            } catch (Exception e) {
//            }
            kafkaTemplate.send("Tm", "2020-11-20 11:16:32;010104;方位限制;方位无限制;无 ");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:32;010104;俯仰限制;俯仰无限制;无");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:32;010104;方位角;" + (000.000 + random) + ";度");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:32;010104;俯仰角;" + (000.000 + random) + ";度");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:32;010104;AGC;+7.5;伏特 ");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:33;010104;俯仰方位转动;无转动;无 ");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:33;010104;命令执行;无命令;无 ");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:33;010104;驱动上电情况;驱动未上电;无 ");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:33;010104;系统状态;系统未就绪;无 ");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:33;010101;MUTE状态;MUTE;无");
            kafkaTemplate.send("Tm", "2020-11-20 11:16:33;010101;衰减 ;" + (000.000 + random) + ";dB");

        }

    }
}




