package com.example.dubbo.provider.service.impl;


import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Threshold;
import com.example.dubbo.api.service.ThresholdService;
import com.example.dubbo.provider.dao.ThresholdDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengying on 2017/9/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@DubboService(version = "1.0.0")
@Service
public class ThresholdServiceImpl implements ThresholdService {

    private static final Logger log = LoggerFactory.getLogger(ThresholdServiceImpl.class);
    @Resource
    private ThresholdDao thresholdDao;


    @Override
    public BaseResponse pageThreshold(Integer page, Integer size) {
        //TODO:分页组件-第pageNo页，pageSize条数目数据
        PageHelper.startPage(page, size);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<Threshold> thresholds = thresholdDao.findAll();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            for (Threshold threshold : thresholds) {
                if (threshold.getTime() != null) {
                    threshold.setDatetime(simpleDateFormat.format(threshold.getTime()));
                }
            }

            PageInfo<Threshold> pageInfo = new PageInfo(thresholds);

            response.setData(pageInfo);
        } catch (Exception e) {
            log.error("门限分页查询服务-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse insertThreshold(Threshold threshold) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            if (threshold != null&&StringUtils.isNotBlank(threshold.getEquipmentId())&&StringUtils.isNotBlank(threshold.getTelemetryName())) {
               Threshold temp=thresholdDao.findByEquipmentIdAndName(threshold.getEquipmentId(),threshold.getTelemetryName());
                if(temp==null) {
                    thresholdDao.insert(threshold);
                    log.info("新增门限：{} ", threshold);
                }else if(temp!=null){
                    response = new BaseResponse(StatusCode.Repeat);
                    response.setMsg("门限已存在");
                }
            }
            else{
                response = new BaseResponse(StatusCode.Fail);
            }
        } catch (Exception e) {
            log.error("新增门限-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse deletByID(Integer id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            if(id==null){
                response = new BaseResponse(StatusCode.Fail);
                response.setMsg("id不能为空");
            }else if(id>=15&&id<=26){
                response = new BaseResponse(StatusCode.Fail);
                response.setMsg("您没有删除该门限的权限");

            }else if(id!=null&&(id<15||id>26)){
                thresholdDao.deleteById(id);
                response.setMsg("门限删除成功");
                log.info("删除门限：{} ", id);
            }

        } catch (Exception e) {
            log.error("删除门限-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse updateThreshold(Threshold threshold) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            if(threshold.getId()==null){
                response = new BaseResponse(StatusCode.Fail);
                response.setMsg("id不能为空");
            }else if(threshold.getId()>=15&&threshold.getId()<=26){
                response = new BaseResponse(StatusCode.Fail);
                response.setMsg("您没有修改该门限的权限");

            }else if(threshold.getId()!=null&&(threshold.getId()<15||threshold.getId()>26)){
                thresholdDao.update(threshold);
                log.info("更新门限：{} ", threshold);
                response.setMsg("门限更新成功");

            }

        } catch (Exception e) {
            log.error("更新门限-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public Threshold findThresholdById(Integer id) {
        Threshold threshold = new Threshold();
        try {
            if (id != null) {
                threshold = thresholdDao.queryById(id);
            }

        } catch (Exception e) {
            log.error("根据id查询门限信息-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());

        }
        return threshold;
    }

    @Override
    public Threshold findThresholdByEqIdAndName(String equipmentId, String telemetryName) {
        Threshold threshold = new Threshold();
        try {
            if (StringUtils.isNotBlank(equipmentId) && StringUtils.isNotBlank(telemetryName)) {

                threshold = thresholdDao.findByEquipmentIdAndName(equipmentId, telemetryName);
            }

        } catch (Exception e) {
            log.error("根据设备id和遥测点名称查询门限信息-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());

        }
        return threshold;
    }

}