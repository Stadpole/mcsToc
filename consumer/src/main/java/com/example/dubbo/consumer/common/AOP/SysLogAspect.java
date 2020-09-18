package com.example.dubbo.consumer.common.AOP;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.example.dubbo.api.common.request.OperationLogRequest;
import com.example.dubbo.api.service.OperationLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Stadpole on 2020/8/26 16:10
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Reference(version = "1.0.0")
    private OperationLogService operationLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.example.dubbo.consumer.common.AOP.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        OperationLogRequest operationLog = new OperationLogRequest();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();

            operationLog.setOperation_detail(value);//保存获取的操作

        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
//        String methodName = method.getName();
//        operationLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        String paramter = "";
        if (arguments != null) {
            try {
                paramter = JSONObject.toJSONString(arguments);
            } catch (Exception e) {
                paramter = arguments.toString();
            }
        }
        operationLog.setParam(paramter);

        operationLog.setTime(new Date());
        //获取用户名



        //调用service保存SysLog实体类到数据库
        operationLogService.save(operationLog);
    }

}
