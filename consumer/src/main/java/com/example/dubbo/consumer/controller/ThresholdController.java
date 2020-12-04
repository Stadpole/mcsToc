package com.example.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.OperationLog;
import com.example.dubbo.api.entity.Threshold;
import com.example.dubbo.api.service.OperationLogService;
import com.example.dubbo.api.service.ThresholdService;
import com.example.dubbo.consumer.common.BaseCommonController;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/threshold")
public class ThresholdController extends BaseCommonController {

    private static final Logger log = LoggerFactory.getLogger(ThresholdController.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @DubboReference(version = "1.0.0")
    private ThresholdService thresholdService;
    @DubboReference(version = "1.0.0")
    private OperationLogService operationLogService;


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
            PageInfo<Threshold> pageInfo = (PageInfo<Threshold>) response.getData();
            List<Threshold> list = pageInfo.getList();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            for (Threshold threshold : list) {
//                if(threshold.getTime()!=null) {
//                   threshold.setDatetime(simpleDateFormat.format(threshold.getTime()));
//                }
//            }

            if (response != null && response.getCode().equals(0)) {
                return sendMessage("0", "success", response.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }

    /**
     * 根据设备id和遥测点名称查询门限信息
     * @param equipmentId  设备id
     * @param telemetryName  遥测点名称
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findOne(@RequestParam String equipmentId, @RequestParam String telemetryName) {

        try {

           Threshold threshold=thresholdService.findThresholdByEqIdAndName(equipmentId,telemetryName);
                   if (threshold!=null) {
                return sendMessage("0", "success", threshold);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }

    /**
     * 添加门限
     *
     * @param id 当前登录的用户id
     * @return
     */

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertThreshold(@RequestParam Integer id, @RequestBody Threshold threshold) {
        //TODO:调用服务提供方thresholdService提供的添加门限
        try {
            BaseResponse response = thresholdService.insertThreshold(threshold);
            if (response.getCode() == 0) {
//                OperationLog operationLog = new OperationLog();
//                operationLog.setUserId(id);
//                operationLog.setOperationDetail("新增门限成功");
//                operationLog.setMethod("门限操作");
//                operationLog.setParam("站名:" + threshold.getStationName() + " 设备名:" + threshold.getEquipmentName() + " 遥测点名称:" + threshold.getTelemetryName() +
//                        " 高红门限值:" + threshold.getHighRed() + " 高黄门限值:" + threshold.getHighYellow() +
//                        " 低黄门限值:" + threshold.getLowYellow() + " 低红门限值:" + threshold.getLowRed());
//                operationLog.setTime(System.currentTimeMillis());
//                operationLogService.save(operationLog);
                return sendMessage("0", "success", response.getData());
            }else if(response.getCode()==1){
                return sendMessage("1", "repeat", response.getMsg());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }

    /**
     * 更新门限
     *
     * @param id 当前登录的用户id
     * @return
     */

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateThreshold(@RequestParam Integer id, @RequestBody Threshold threshold) {
        //TODO:调用服务提供方userService提供的更新门限
        try {
            BaseResponse response = thresholdService.updateThreshold(threshold);
            if (response.getCode() == 0) {
                OperationLog operationLog = new OperationLog();
                operationLog.setUserId(id);
                operationLog.setOperationDetail("更新门限成功，字段为空说明该信息未修改");
                operationLog.setMethod("门限操作");
                operationLog.setParam(" 高红门限值:" + threshold.getHighRed() + " 高黄门限值:" + threshold.getHighYellow() +
                        " 低黄门限值:" + threshold.getLowYellow() + " 低红门限值:" + threshold.getLowRed());
                operationLog.setTime(System.currentTimeMillis());
                operationLogService.save(operationLog);
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
     * @param userId 当前登录用户id
     * @param id     要删除的门限id
     * @return
     */

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteThreshold(@RequestParam Integer userId, @RequestParam Integer id) {
        try {

            BaseResponse response = thresholdService.deletByID(id);
            if (response.getCode() == 0) {
                OperationLog operationLog = new OperationLog();
                operationLog.setUserId(userId);
                operationLog.setOperationDetail("删除门限成功");
                operationLog.setMethod("门限操作");
                operationLog.setParam("门限id:" + id);
                operationLog.setTime(System.currentTimeMillis());
                operationLogService.save(operationLog);
                return sendMessage("0", "success", response.getData());
            }else if(response.getCode()==-1){
                return sendMessage("0", "success", response.getMsg());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendFailMessage();
    }
}

