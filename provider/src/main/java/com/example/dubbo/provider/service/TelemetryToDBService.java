package com.example.dubbo.provider.service;

import com.example.dubbo.provider.common.utils.StringToTelemetryUtil;
import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.service.TelemetryBatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stadpole on 2020/8/13 11:24
 */
@Component
@Slf4j
public class TelemetryToDBService {

    @Autowired
    private StringToTelemetryUtil stringToTelemetryUtil;
    @Autowired
    private TelemetryBatchService telemetryBatchService;
    List<Telemetry> list = new ArrayList<>();

    public void TelemetryToDataBase(String  telemetries) {
        try{
            Telemetry telemetry = stringToTelemetryUtil.ConvertToTelemrtry(telemetries);
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
