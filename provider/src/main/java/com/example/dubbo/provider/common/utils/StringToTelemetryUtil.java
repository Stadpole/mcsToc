package com.example.dubbo.provider.common.utils;

import com.example.dubbo.provider.entity.Telemetry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Stadpole on 2020/8/12 14:59
 */
@Component
public class StringToTelemetryUtil {
    public Telemetry ConvertToTelemrtry(String s) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyyMMdd:HH:mm:ss");
        String[] strings = s.split("_");
        Telemetry telemetry = new Telemetry();
        telemetry.setEquipment_id(Integer.valueOf(strings[0]));
        telemetry.setTelemetry_name(strings[1]);
        telemetry.setEngineering_value(strings[2]);
        telemetry.setUnit(strings[3]);
        telemetry.setTime(format.parse(strings[4]));
        return telemetry;
    }
}
