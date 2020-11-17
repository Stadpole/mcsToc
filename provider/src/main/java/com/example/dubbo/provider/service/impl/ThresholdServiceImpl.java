package com.example.dubbo.provider.service.impl;

import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Threshold;
import com.example.dubbo.api.entity.UserInfo;
import com.example.dubbo.api.service.ThresholdService;
import com.example.dubbo.provider.common.BeanUtills;
import com.example.dubbo.provider.dao.ThresholdDao;
import com.example.dubbo.provider.dao.UserInfoDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;



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
            PageInfo<Threshold> pageInfo = new PageInfo(thresholds);

            response.setData(pageInfo);
        } catch (Exception e) {
            log.error("门限分页查询服务-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
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
            }else{
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
}