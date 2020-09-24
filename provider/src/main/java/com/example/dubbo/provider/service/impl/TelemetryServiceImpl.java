package com.example.dubbo.provider.service.impl;

import com.example.dubbo.api.common.request.TelemetryRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.TelemetryService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by Stadpole on 2020/8/7 16:15
 */
@Service(version = "1.0.0")
public class TelemetryServiceImpl implements TelemetryService {

    private static final Logger log = LoggerFactory.getLogger(TelemetryServiceImpl.class);


    @Override
    public BaseResponse insertTelemetry(TelemetryRequest telemetryRequest) {
        return null;
    }

    @Override
    public BaseResponse telemetryRealTime() {
        return null;
    }

    @Override
    public BaseResponse telemetryHistory(String equipment_id, String telemetry_name, String startTime, String endTime) {
        return null;
    }
}
