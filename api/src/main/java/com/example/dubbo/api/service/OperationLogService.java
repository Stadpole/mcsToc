package com.example.dubbo.api.service;

import com.example.dubbo.api.common.request.OperationLogRequest;
import com.example.dubbo.api.common.response.BaseResponse;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
//@Path("operation")
public interface OperationLogService {

    //TODO：分页查询日志列表
//    @Path("1")
     BaseResponse pageOperationLog(Integer page, Integer size,Integer userId);
     void save(OperationLogRequest operationLogRequest);

}