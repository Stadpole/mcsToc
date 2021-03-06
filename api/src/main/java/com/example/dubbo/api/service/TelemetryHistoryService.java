package com.example.dubbo.api.service;

import com.example.dubbo.api.common.response.BaseResponse;

/**
 * Created by Stadpole on 2020/8/7 16:12
 */
//@Path("intert")
public interface TelemetryHistoryService {

    /**
     * 遥测回放
     */
    BaseResponse telemetryHistory(String equipment_id, String telemetry_name, String startTime, String endTime);

    BaseResponse telemetryNHistory(String equipment_id, String telemetry_name, String startTime, String endTime);
}
