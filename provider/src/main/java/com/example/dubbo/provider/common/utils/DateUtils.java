package com.example.dubbo.provider.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Stadpole on 2020/9/18 10:51
 */
@Component
@Slf4j
public class DateUtils {

    /**
     * 两个时间之差
     * @param startDate
     * @param endDate
     * @return 分钟
     */
    public  Integer getBetweenMinutes(Date startDate, Date endDate) {
        Integer minutes = 0;
        try {
            if(startDate!=null&&endDate!=null) {
                long ss = 0;
                if(startDate.before(endDate)) {
                    ss = endDate.getTime() - startDate.getTime();
                }else {
                    ss = startDate.getTime() - endDate.getTime();
                }
                minutes = Integer.valueOf((int) (ss/(60*1000))) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minutes;
    }

}

