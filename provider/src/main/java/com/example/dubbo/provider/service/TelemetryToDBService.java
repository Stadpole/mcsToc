package com.example.dubbo.provider.service;

import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.provider.common.utils.StringToObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stadpole on 2020/8/13 11:24
 */
@Component
@Slf4j
public class TelemetryToDBService {

    @Autowired
    private StringToObjectUtil stringToObjectUtil;
    @Autowired
    private TelemetryBatchService telemetryBatchService;
    List<Telemetry> list = new ArrayList<>();

    public void TelemetryToDataBase(String  telemetries) {
        try{
            Telemetry telemetry = stringToObjectUtil.ConvertToTelemrtry(telemetries);
            if (list.size() == 10) {
                telemetryBatchService.insertTelemetryBatch(list);
                list.clear();
            } else {
                list.add(telemetry);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
