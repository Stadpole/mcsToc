package com.example.dubbo.provider.service;

import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.provider.dao.TelemetryDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Stadpole on 2020/8/13 9:36
 */
@Component
@Slf4j
public class TelemetryBatchService {
    @Resource
    private TelemetryDao telemetryDao;

    public Boolean insertTelemetryBatch(List<Telemetry> list){
        Boolean result=false;
        try {
            log.info("遥测批处理插入开始"+list);
            if(list.size()>0){
                result=telemetryDao.insertTelemetryBatchCode(list);
                return result;
            }

        }catch (Exception e){
            log.info("遥测批处理插入失败");
            e.printStackTrace();
        }
        return result;
    }

}
