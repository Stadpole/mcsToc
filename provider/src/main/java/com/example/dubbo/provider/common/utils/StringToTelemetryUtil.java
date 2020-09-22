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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strings = s.split(";");
        Telemetry telemetry = new Telemetry();
        telemetry.setTime(format.parse(strings[0]));
        telemetry.setEquipment_id(strings[1]);
        telemetry.setTelemetry_name(strings[2]);
        telemetry.setEngineering_value(strings[3]);
        telemetry.setUnit(strings[4]);
        return telemetry;
    }

}
