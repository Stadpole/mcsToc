package com.example.dubbo.api.service;

import com.example.dubbo.api.common.request.OperationLogRequest;
import com.example.dubbo.api.common.request.UserInfoRequest;
import com.example.dubbo.api.common.response.BaseResponse;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
public interface OperationLogService {

    //TODO：分页查询日志列表
    public BaseResponse pageOperationLog(Integer page, Integer size);

    //TODO：添加日志
    public BaseResponse insertOperationLog(OperationLogRequest operationLogRequest);



}