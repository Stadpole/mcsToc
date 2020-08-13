package com.example.dubbo.api.service;

import com.example.dubbo.api.common.request.TelemetryRequest;
import com.example.dubbo.api.common.response.BaseResponse;

/**
 * Created by Stadpole on 2020/8/7 16:12
 */
public interface TelemetryService {
    //TODO：添加遥测
    public BaseResponse insertTelemetry(TelemetryRequest telemetryRequest);
}
