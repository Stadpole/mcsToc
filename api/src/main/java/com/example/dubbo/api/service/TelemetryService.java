package com.example.dubbo.api.service;

import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Telemetry;

/**
 * Created by Stadpole on 2020/8/7 16:12
 */
//@Path("intert")
public interface TelemetryService {
    /**
     * 遥测添加
     *
     */
     BaseResponse insertTelemetry(Telemetry telemetryRequest);
    /**
     * 实时遥测查看
     *
     *
     */
     BaseResponse telemetryRealTime();

    /**
     * 遥测回放
     */
    BaseResponse telemetryHistory(String equipment_id,String telemetry_name,String startTime,String endTime);
}
