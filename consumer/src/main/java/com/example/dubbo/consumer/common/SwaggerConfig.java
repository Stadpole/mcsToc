package com.example.dubbo.consumer.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Stadpole on 2020/8/18 10:28
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    Boolean swaggerEnabled = true;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)//true
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.dubbo.consumer.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(" 测试使用 ")
                //创建人
                .contact(new Contact("clc", "http://www.baidu.com", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }


}