package com.example.dubbo.provider.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.request.ThresholdRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.ThresholdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mengying on 2017/9/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@Service(version = "1.0.0")
public class ThresholdServiceImpl implements ThresholdService {

    private static final Logger log = LoggerFactory.getLogger(ThresholdServiceImpl.class);


    @Override
    public BaseResponse pageThreshold(Integer page, Integer size) {
        return null;
    }

    @Override
    public BaseResponse insertThreshold(ThresholdRequest threshold) {
        return null;
    }

    @Override
    public BaseResponse deletByID(Integer id) {
        return null;
    }

    @Override
    public BaseResponse updateThreshold(ThresholdRequest threshold) {
        return null;
    }
}