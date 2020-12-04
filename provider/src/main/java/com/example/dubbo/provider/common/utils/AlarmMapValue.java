package com.example.dubbo.provider.common.utils;

import com.example.dubbo.api.entity.Alarm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Stadpole on 2020/11/4 10:21
 */
@Component
@Slf4j
public class AlarmMapValue {

    private Alarm alarm;
    private boolean aBoolean;

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
