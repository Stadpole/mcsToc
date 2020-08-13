package com.example.dubbo.provider.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.request.OperationLogRequest;

import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.OperationLogService;

import com.example.dubbo.provider.entity.OperationLog;

import com.example.dubbo.provider.repository.OperationLogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by mengying on 2017/9/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@Service(version = "1.0.0")
public class OperationLogServiceImpl implements OperationLogService {

    private static final Logger log = LoggerFactory.getLogger(OperationLogServiceImpl.class);
    @Autowired
    private OperationLogRepository operationLogRepository;

    @Override
    public BaseResponse pageOperationLog(Integer page, Integer size) {
        return null;
    }

    @Override
    public BaseResponse insertOperationLog(OperationLogRequest operationLogRequest) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(operationLogRequest!=null) {
                OperationLog operationLog=new OperationLog();
                BeanUtils.copyProperties(operationLogRequest, operationLog);
                operationLogRepository.save(operationLog);
                log.info("新增日志：{} ", operationLog);
            }

        }catch (Exception e){
            log.error("新增日志-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}