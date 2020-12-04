package com.example.dubbo.api.service;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Threshold;
import com.example.dubbo.api.entity.UserInfo;

/**
 * Created by Stadpole on 2020/7/29 16:24
 */
//@Path("threshold")
public interface ThresholdService {


    /**
     * 分页查询门限列表
     * @Param page 页码
     *@Param size 每页显示的个数
     */
     BaseResponse pageThreshold(Integer page, Integer size);


    /**
     * 添加门限
     *
     */
     BaseResponse insertThreshold(Threshold threshold);

    /**
     *通过id删除门限
     *
     */
     BaseResponse deletByID(Integer id);

     /**
     *更新门限
     *
     */
     BaseResponse updateThreshold(Threshold threshold);
    /**
     *根据id查找门限信息
     *
     */
     Threshold findThresholdById(Integer id);
    /**
     *根据设备id和遥测点名称查找门限信息
     *
     */
    Threshold findThresholdByEqIdAndName(String equipmentId,String telemetryName);
}