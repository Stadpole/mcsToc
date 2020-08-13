package com.example.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.request.TelemetryRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.TelemetryService;
import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.entity.Threshold;
import com.example.dubbo.provider.repository.TelemetryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Stadpole on 2020/8/7 16:15
 */
@Service(version = "1.0.0")
public class TelemetryServiceImpl implements TelemetryService {

    private static final Logger log = LoggerFactory.getLogger(TelemetryServiceImpl.class);

    @Autowired
    private TelemetryRepository telemetryRepository;
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
}
