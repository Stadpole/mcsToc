package com.example.dubbo.provider.service.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class KfkaProducer  {

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

       // kafkaTemplate.send("testTocpic", ss[random]+"_俯仰方位角转动_无转动_无_20200811:09:17:59");
        Date date = new Date();//获取当前的日期
        for(int i=1;i<10000;i++) {

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd:HH:mm:ss");//设置日期格式
            String str = df.format(date);//获取String类型的时间
          int random=(int)(Math.random()*10);



          kafkaTemplate.send("22", "010104" + "_俯仰角_" + random + "_无_"+str);
//          kafkaTemplate.send("11", "010104" + "_俯仰角_" + random + "_无_"+formatter.format(date));
//          kafkaTemplate.send("11", "010104" + "_俯仰角_" + random + "_无_"+formatter.format(date));
//          kafkaTemplate.send("11", "010104" + "_俯仰角_" + random + "_无_"+formatter.format(date));
//          kafkaTemplate.send("11", "010104" + "_俯仰角_" + random + "_无_"+formatter.format(date));
//          kafkaTemplate.send("11", "010104" + "_俯仰角_" + random + "_无_"+formatter.format(date));
//          kafkaTemplate.send("11", "010104" + "_俯仰角_" + random + "_无_20200811:09:17:59");
      }
    }


}

