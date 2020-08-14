package com.example.dubbo.provider.service;

import com.example.dubbo.provider.common.utils.StringToTelemetryUtil;
import com.example.dubbo.provider.entity.Telemetry;
import com.example.dubbo.provider.entity.Threshold;
import com.example.dubbo.provider.mapper.ThresholdMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stadpole on 2020/8/13 13:51
 */
@Component
@Slf4j
public class AlarmByThresholdService {

    @Autowired
    private StringToTelemetryUtil stringToTelemetryUtil;
    @Autowired(required = false)
    private ThresholdMapper thresholdMapper;

    public String alarmByThreshold(String telemetries) {
        try {
            Telemetry telemetry = stringToTelemetryUtil.ConvertToTelemrtry(telemetries);
            List<Threshold> lists = thresholdMapper.SelecALL();
            if (!lists.isEmpty() && telemetry != null && telemetry.getEquipment_id() != null && telemetry.getEngineering_value() != null) {
                for (Threshold threshold : lists) {
                    while (threshold.getEquipment_id() == telemetry.getEquipment_id()) {
                        Double temp = Double.valueOf(telemetry.getEngineering_value());
                        //TODO:高红
                        if (temp > threshold.getHigh_red()) {

                        }//TODO:高黄
                        else if (temp < threshold.getHigh_yellow()) {

                        }
                        //TODO:低红
                        else if (temp < threshold.getHigh_red() && temp > threshold.getLow_red()) {


                        }
                        //TODO:低黄
                        else if (temp < threshold.getLow_yellow() && temp > threshold.getHigh_yellow()) {


                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
