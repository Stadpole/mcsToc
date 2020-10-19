package com.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Alarm;
import com.example.dubbo.api.service.AlarmService;
import com.example.dubbo.consumer.common.BaseCommonController;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/alarm")
public class AlarmController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(AlarmController.class);

    @DubboReference(version = "1.0.0")
    private AlarmService alarmService;

    /**
     * 告警列表查询-分页查询
     *
     * @return
     */

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String Page(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String equipment_id, @RequestParam String startTime,@RequestParam String endTime) {
        if (page == null || size == null || page <= 0 || size <= 0) {
            page = 1;
            size = 2;
        }
    if(StringUtils.isEmpty(startTime)&&StringUtils.isEmpty(endTime)){
      return sendFailMessage();
      }

        //TODO:调用服务提供方alarmService提供的列表查询-分页查询功能
        try {
            Gson gson=new Gson();
            BaseResponse response = alarmService.pageAlarm(page,size,equipment_id,startTime,endTime);
            if (response != null && response.getCode().equals(0)) {
                return sendMessage("0", "success", response.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
    /**
     * 更新告警状态  确认
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateThreshold(@RequestBody Alarm alarmRequest) {
        //TODO:调用服务提供方alarmRequest提供的更新门限
        try {
            BaseResponse response = alarmService.updateAlarm(alarmRequest);
            if(response.getCode()==0) {
                return sendMessage("0", "success", response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
}

