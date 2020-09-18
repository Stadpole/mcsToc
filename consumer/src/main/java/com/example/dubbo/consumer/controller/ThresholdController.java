package com.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.api.common.request.ThresholdRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.ThresholdService;
import com.example.dubbo.consumer.common.AOP.MyLog;
import com.example.dubbo.consumer.common.BaseCommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/threshold")
public class ThresholdController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(ThresholdController.class);

    @Reference(version = "1.0.0")
    private ThresholdService thresholdService;

    /**
     * 门限列表查询-分页查询
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String Page(@RequestParam Integer page, @RequestParam Integer size) {
        if (page == null || size == null || page <= 0 || size <= 0) {
            page = 1;
            size = 2;
        }
        //TODO:调用服务提供方thresholdService提供的列表查询-分页查询功能
        try {
            BaseResponse response = thresholdService.pageThreshold(page, size);
            if (response != null && response.getCode().equals(0)) {
                return sendMessage("0", "success", response.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
    /**
     * 添加门限
     *
     * @return
     */
    @MyLog(value = "新增门限")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertThreshold(@RequestBody ThresholdRequest thresholdRequest) {
        //TODO:调用服务提供方thresholdService提供的添加门限
        try {
             BaseResponse response = thresholdService.insertThreshold(thresholdRequest);
            if(response.getCode()==0) {
                return sendMessage("0", "success", response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }

    /**
     * 更新门限
     *
     * @return
     */
    @MyLog(value = "更新门限信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateThreshold(@RequestBody ThresholdRequest thresholdRequest) {
        //TODO:调用服务提供方userService提供的更新门限
        try {
            BaseResponse response = thresholdService.updateThreshold(thresholdRequest);
            if(response.getCode()==0) {
                return sendMessage("0", "success", response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
    /**
     * 删除门限
     *
     * @return
     */
    @MyLog(value = "删除门限信息")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteThreshold(@RequestParam Integer id) {
        //TODO:调用服务提供方userService提供的删除门限
        try {
            BaseResponse response = thresholdService.deletByID(id);
            if(response.getCode()==0) {
                return sendMessage("0", "success", response.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
}

