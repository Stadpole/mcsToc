package com.example.dubbo.consumer;


import com.example.dubbo.consumer.zookeeper.ZookeeperObject;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ConsumerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConsumerApplication.class, args);
        // 新建ZookeeperObject对象并建立连接
        ZookeeperObject zkObject = new ZookeeperObject();

        zkObject.GetZkAddress();  // 获取集群位置

        zkObject.ConnectZookeeper(2000);  // 连接Zookeeper，timout为2000ms

        // 挂载自身节点
        String path = "/dubbo/com.example.dubbo.api.service.UserService/Service/test";
        zkObject.createNode(path, "172.16.18.214:9999");
    }

}
