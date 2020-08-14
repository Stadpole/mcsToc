package com.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.api.common.request.ThresholdRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.TelemetryService;
import com.example.dubbo.api.service.ThresholdService;
import com.example.dubbo.consumer.common.BaseCommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/telemetry")
public class TelemetryController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(TelemetryController.class);

    @Reference(version = "1.0.0")
    private TelemetryService telemetryService;

    /**
     * 实时遥测查询
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String findTelemetry() {
        //TODO:调用服务提供方TelemetryService查询实时遥测
        try {
            BaseResponse response =telemetryService.telemetryRealTime();

                return sendMessage("0", "success", response.getData());
            }catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
}

