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
 * Created by Stadpole on 2020/9/21.
 */
@RestController
@RequestMapping(value = "/telemetry")
public class TelemetryController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(TelemetryController.class);

    @Reference(version = "1.0.0")
    private TelemetryService telemetryService;
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
            BaseResponse response =telemetryService.telemetryRealTime();

                return sendMessage("0", "success", response.getData());
            }catch (Exception e) {
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
    public String playbackTelemetry(@RequestParam  String equipment_id,@RequestParam String telemetry_name,@RequestParam String startTime,@RequestParam String endTime) {
        //TODO:调用服务提供方TelemetryService查询实时遥测
        try {
            BaseResponse response =telemetryService.telemetryHistory(equipment_id, telemetry_name, startTime, endTime);
            LoggerFactory.getLogger(TelemetryController.class).warn("查询耗时:"+(System.currentTimeMillis()-start));
            return sendMessage("0", "success", response.getData());

        }catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
}

