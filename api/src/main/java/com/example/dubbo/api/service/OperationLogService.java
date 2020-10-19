package com.example.dubbo.api.service;

import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.OperationLog;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
//@Path("operation")
public interface OperationLogService {

  /**
   * 分页查询操作日志，可通过用户id筛选
   *
   * */
     BaseResponse pageOperationLog(Integer page, Integer size,Integer userId,String method,String startTime,String endTime);
    /**
     * 操作日志保存
     *
     * */
     void save(OperationLog operationLogRequest);

}