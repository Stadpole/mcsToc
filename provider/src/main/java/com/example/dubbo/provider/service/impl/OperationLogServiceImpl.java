package com.example.dubbo.provider.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.request.OperationLogRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.OperationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by mengying on 2017/9/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@Service(version = "1.0.0")
public class OperationLogServiceImpl implements OperationLogService {

    private static final Logger log = LoggerFactory.getLogger(OperationLogServiceImpl.class);


    @Override
    public BaseResponse pageOperationLog(Integer page, Integer size, Integer userId) {
        return null;
    }

    @Override
    public void save(OperationLogRequest operationLogRequest) {

    }
}


