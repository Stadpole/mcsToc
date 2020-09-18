package com.example.dubbo.consumer.common.AOP;


import java.lang.annotation.*;

/**
 * 自定义注解类 Created by Stadpole on 2020/8/26 16:09
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {
    String value() default "";

}
