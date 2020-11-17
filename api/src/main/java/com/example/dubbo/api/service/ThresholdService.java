package com.example.dubbo.api.service;


import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Threshold;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
public interface ThresholdService {
    /**
     * 遥测门限分页查询
     * @param page
     * @param size
     * @return
     */
    BaseResponse pageThreshold(Integer page, Integer size);

    /**
     * 更新门限信息
     * @param threshold     *
     * @return
     */
    BaseResponse updateThreshold(Threshold threshold);

}