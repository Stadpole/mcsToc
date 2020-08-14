package com.example.dubbo.provider.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

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
        String [] ss={"010101","010102","010103","010104","010105","010106","010107","010108","010109","010110"};
        int random=(int)(Math.random()*10);
        kafkaTemplate.send("testTocpic", ss[random]+"_俯仰方位角转动_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_无_命令执行_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_命令执行_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_无_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_无命令_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_无_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_无1_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_无2_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_无3_无转动_无_20200811:09:17:59");
        kafkaTemplate.send("testTocpic", ss[random]+"_无4_无转动_无_20200811:09:17:59");

    }


}

