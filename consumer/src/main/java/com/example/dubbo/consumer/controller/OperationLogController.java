package com.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.api.common.request.ThresholdRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.OperationLogService;
import com.example.dubbo.api.service.ThresholdService;
import com.example.dubbo.consumer.common.BaseCommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/operationLog")
public class OperationLogController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(OperationLogController.class);

    @Reference(version = "1.0.0")
    private OperationLogService operationLogService;

    /**
     * 操作日志列表查询-分页查询
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String Page(@RequestParam Integer page, @RequestParam Integer size,Integer userId) {
        if (page == null || size == null || page <= 0 || size <= 0) {
            page = 1;
            size = 2;
        }
        //TODO:调用服务提供方operationLogService提供的列表查询-分页查询功能
        try {
            BaseResponse response = operationLogService.pageOperationLog(page, size,userId);
            if (response != null && response.getCode().equals(0)) {
                return sendMessage("0", "success", response.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
}

