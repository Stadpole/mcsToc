package com.example.dubbo.provider.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Stadpole on 2020/5/27 8:57
 */
@RestController
@Controller
public class KafkaController {

    @Autowired
    private KfkaProducer producer;

    @RequestMapping("/testSendMsg")
    @ResponseBody
    public String testSendMsg(){
        producer.send();
        return "success";
    }

}

