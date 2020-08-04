package com.example.dubbo.provider.common;

import com.example.dubbo.provider.entity.OperationLog;
import com.example.dubbo.provider.repository.OperationLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by Stadpole on 2020/8/4 15:48
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private OperationLogRepository operationLogRepository;

    @Pointcut("@annotation(com.example.dubbo.provider.common.Log)")
    public void pointcut() { }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationLog operationLog = new OperationLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            operationLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        operationLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            operationLog.setParams(params);
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        operationLog.setIp(IPUtils.getIpAddr(request));
        // 模拟一个用户名

        // 保存系统日志
        operationLogRepository.save(operationLog);
    }
}
