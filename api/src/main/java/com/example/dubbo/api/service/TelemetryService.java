package com.example.dubbo.api.service;

import com.example.dubbo.api.common.request.TelemetryRequest;
import com.example.dubbo.api.common.response.BaseResponse;

import javax.ws.rs.Path;

/**
 * Created by Stadpole on 2020/8/7 16:12
 */
//@Path("intert")
public interface TelemetryService {
    //TODO：添加遥测
//    @Path("1")
    public BaseResponse insertTelemetry(TelemetryRequest telemetryRequest);
    //TODO：实时遥测查询
//    @Path("2")
    public BaseResponse telemetryRealTime();

    //TODO：遥测回放
    BaseResponse telemetryHistory(String equipment_id,String telemetry_name,String startTime,String endTime);
}
