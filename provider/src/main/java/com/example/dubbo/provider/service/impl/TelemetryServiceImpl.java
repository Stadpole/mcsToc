package com.example.dubbo.provider.service.impl;

import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.api.entity.Threshold;
import com.example.dubbo.api.service.TelemetryService;
import com.example.dubbo.provider.common.utils.MyDateUtils;
import com.example.dubbo.provider.dao.TelemetryDao;
import com.example.dubbo.provider.service.TelemetryToMapService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stadpole on 2020/8/7 16:15
 */
@DubboService(version = "1.0.0")
@Service
public class TelemetryServiceImpl implements TelemetryService {

    private static final Logger log = LoggerFactory.getLogger(TelemetryServiceImpl.class);

    @Resource
    private TelemetryDao telemetryDao;
    @Resource
    private TelemetryToMapService telemetryToMapService;

    @Resource
    private MyDateUtils dateUtils;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm");


    @Override
    public BaseResponse insertTelemetry(Telemetry telemetryRequest) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            if (telemetryRequest != null) {

                telemetryDao.insert(telemetryRequest);
                log.info("新增遥测：{} ", telemetryRequest);
            }
        } catch (Exception e) {
            log.error("新增遥测-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;

    }

    @Override
    public BaseResponse telemetryRealTime() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<Telemetry> telemetryList = new ArrayList<>();
            ConcurrentHashMap<String, Telemetry> map = telemetryToMapService.telemetryReal();
//            log.info("hashMapSize"+map.size());
            for (Map.Entry<String, Telemetry> entry : map.entrySet()) {
                telemetryList.add(entry.getValue());
                log.info("---------"+entry.getValue().getTelemetryName());
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            for (Telemetry telemetry : telemetryList) {
                if(telemetry.getTime()!=null) {
                    telemetry.setdTime(simpleDateFormat.format(telemetry.getTime()));
                }
            }
            response.setData(telemetryList);
        } catch (Exception e) {
            log.error("实时遥测-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

}
