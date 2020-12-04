package com.example.providerplayback;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDubbo
@SpringBootApplication
@EnableAsync
@EnableScheduling
@MapperScan(basePackages = "com.example.providerplayback.dao")
public class ProviderPlaybackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderPlaybackApplication.class, args);
    }

}
