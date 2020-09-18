package com.example.dubbo.api.service;

import com.example.dubbo.api.common.request.ThresholdRequest;
import com.example.dubbo.api.common.response.BaseResponse;

import javax.ws.rs.Path;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
//@Path("threshold")
public interface ThresholdService {

    //TODO：分页查询门限列表
//    @Path("1")
    public BaseResponse pageThreshold(Integer page, Integer size);

    //TODO：添加门限
//    @Path("2")
    public BaseResponse insertThreshold(ThresholdRequest threshold);

    //TODO：通过id删除门限
//    @Path("3")
    public BaseResponse deletByID(Integer id);

    //TODO：更新门限
//    @Path("4")
    public BaseResponse updateThreshold(ThresholdRequest threshold);
}