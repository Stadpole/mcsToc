package com.example.dubbo.provider.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.request.ThresholdRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.ThresholdService;
import com.example.dubbo.provider.common.BeanUtills;
import com.example.dubbo.provider.entity.Threshold;
import com.example.dubbo.provider.mapper.ThresholdMapper;
import com.example.dubbo.provider.repository.ThresholdRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mengying on 2017/9/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@Service(version = "1.0.0")
public class ThresholdServiceImpl implements ThresholdService {

    private static final Logger log = LoggerFactory.getLogger(ThresholdServiceImpl.class);

    @Autowired
    private ThresholdRepository thresholdRepository;
    @Autowired
    private ThresholdMapper thresholdMapper;

    @Override
    public BaseResponse pageThreshold(Integer page, Integer size) {
        //TODO:分页组件-第pageNo页，pageSize条数目数据
        PageHelper.startPage(page,size);
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            List<Threshold> thresholds = thresholdMapper.findALL();

            PageInfo<Threshold> pageInfo = new PageInfo(thresholds);

            response.setData(pageInfo);
        }catch (Exception e){
            log.error("门限分页查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse insertThreshold(ThresholdRequest threshold) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(threshold!=null) {
                Threshold entity=new Threshold();
                BeanUtills.copyProperties(threshold, entity);
                thresholdRepository.save(entity);
                log.info("新增门限：{} ", entity);
            }
        }catch (Exception e){
            log.error("新增门限-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse deletByID(Integer id) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            Threshold threshold=thresholdRepository.findById(id);
            if(threshold!=null) {
                thresholdRepository.delete(threshold);
                log.info("删除门限：{} ", threshold);
            }

        }catch (Exception e){
            log.error("删除门限-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    @Override
    public BaseResponse updateThreshold(ThresholdRequest threshold) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(threshold.getId()!=null) {
                Threshold entity=thresholdRepository.findById(threshold.getId());
                BeanUtills.copyProperties(threshold, entity);
                thresholdRepository.save(entity);
                log.info("更新门限：{} ", entity);
            }

        }catch (Exception e){
            log.error("更新门限-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

}