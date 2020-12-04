package com.example.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableDubbo
@EnableScheduling
@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = "com.example.dubbo.provider.dao")
public class ProviderApplication {

    public static void main(String[] args) {
//        Thread tread = new Thread(new ParamDBsave());
//        tread.start();
        SpringApplication.run(ProviderApplication.class, args);

    }

}