package com.example.dubbo.provider.service;

import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.request.TelemetryRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.TelemetryService;
import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.repository.TelemetryRepository;
import com.sun.corba.se.impl.orb.ParserTable;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stadpole on 2020/8/7 16:15
 */
@Service(version = "1.0.0")
public class TelemetryServiceImpl implements TelemetryService {

    private static final Logger log = LoggerFactory.getLogger(TelemetryServiceImpl.class);

    @Autowired
    private TelemetryRepository telemetryRepository;
    @Autowired
    private TelemetryToMapService telemetryToMapService;

    @Override
    public BaseResponse insertTelemetry(TelemetryRequest telemetryRequest) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(telemetryRequest!=null) {
                Telemetry entity=new Telemetry();
                BeanUtils.copyProperties(telemetryRequest, entity);
                telemetryRepository.save(entity);
                log.info("新增遥测：{} ", entity);
            }
        }catch (Exception e){
            log.error("新增遥测-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;

    }

    @Override
    public BaseResponse telemetryRealTime() {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            List<Telemetry> telemetryList=new ArrayList<>();
           HashMap<Integer,Telemetry> hashMap=telemetryToMapService.telemetryReal();
            for (Map.Entry<Integer, Telemetry> entry : hashMap.entrySet()) {
              telemetryList.add(entry.getValue());
            }
            response.setData(telemetryList);
        }catch (Exception e){
            log.error("新增遥测-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}
