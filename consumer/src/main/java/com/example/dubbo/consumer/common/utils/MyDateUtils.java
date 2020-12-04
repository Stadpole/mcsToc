package com.example.dubbo.consumer.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by Stadpole on 2020/9/18 10:51
 */
@Component
@Slf4j
public class MyDateUtils {

    /**
     * 两个时间之差
     * @param startDate
     * @param endDate
     * @return 分钟
     */
    public  Long getBetweenMinutes(long startDate, long endDate) {
        long timeDifference = endDate-startDate;
        long minutes = timeDifference/6000;
        return minutes;
    }

}

