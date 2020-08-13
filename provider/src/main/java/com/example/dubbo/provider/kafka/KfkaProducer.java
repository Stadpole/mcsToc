package com.example.dubbo.provider.kafka;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class KfkaProducer {

    private static Logger logger = LoggerFactory.getLogger(KfkaProducer.class);

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
        kafkaTemplate.send("testTocpic", "010104_俯仰方位角转动_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", "010104_无_命令执行_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", "010104_命令执行_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", "010103_无_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", "010104_无命令_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", "010105_无_无转动_无_20200811:09:17:59");

    }


}

