package com.example.dubbo.consumer.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stadpole on 2020/10/21 13:46
 */
@Component
@Slf4j
public class Dateutils {
    public String Dateformat(Date temp){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dt = simpleDateFormat.format(temp);

            return dt;
        }catch (Exception e){
            log.warn(e.getMessage());
        }
        return null;
    }

}
