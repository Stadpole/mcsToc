package com.example.dubbo.provider.service;

import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.mapper.TelemetryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stadpole on 2020/8/13 9:36
 */
@Component
@Slf4j
public class TelemetryBatchService {
    @Autowired(required = false)
    private TelemetryMapper telemetryMapper;

    public Boolean insertTelemetryBatch(List<Telemetry> list){
        Boolean result=false;
        try {
            log.info("遥测批处理插入开始"+list);
            if(list.size()>0){
                result=telemetryMapper.insertTelemetryBatchCode(list);
                return result;
            }

        }catch (Exception e){
            log.info("遥测批处理插入失败");
            e.printStackTrace();
        }
        return result;
    }

}
