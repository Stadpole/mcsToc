package com.example.dubbo.consumer.controller;

import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.TelemetryHistoryService;
import com.example.dubbo.api.service.TelemetryService;
import com.example.dubbo.consumer.common.BaseCommonController;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stadpole on 2020/9/21.
 */
@RestController
@RequestMapping(value = "/telemetry")
public class TelemetryController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(TelemetryController.class);

    @DubboReference(version = "1.0.0")
    private TelemetryService telemetryService;
    @DubboReference(version = "1.0.0")
    private TelemetryHistoryService telemetryHistory;

    long start = System.currentTimeMillis();

    /**
     * 实时遥测查询
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String findTelemetry() {
        //TODO:调用服务提供方TelemetryService查询实时遥测
        try {
            BaseResponse response = telemetryService.telemetryRealTime();

            return sendMessage("0", "success", response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }

    /**
     * 历史遥测查询
     *
     * @return
     */
    @RequestMapping(value = "/playback", method = RequestMethod.GET)
    public String playbackNTelemetry(@RequestParam String equipment_id, @RequestParam String telemetry_name, @RequestParam String startTime, @RequestParam String endTime) {
        //TODO:调用服务提供方TelemetryService查询历史遥测
        try {
            BaseResponse response = telemetryHistory.telemetryNHistory(equipment_id, telemetry_name, startTime, endTime);
            LoggerFactory.getLogger(TelemetryController.class).warn("查询耗时:" + (System.currentTimeMillis() - start));
            return sendMessage("0", "success", response.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
}

