package com.example.dubbo.provider.service.impl;

import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.entity.Telemetry;
import com.example.dubbo.api.service.TelemetryService;
import com.example.dubbo.provider.common.utils.MyDateUtils;
import com.example.dubbo.provider.dao.TelemetryDao;
import com.example.dubbo.provider.service.TelemetryToMapService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Stadpole on 2020/8/7 16:15
 */
@DubboService(version = "1.0.0")
@Service
public class TelemetryServiceImpl implements TelemetryService {

    private static final Logger log = LoggerFactory.getLogger(TelemetryServiceImpl.class);

    @Resource
    private TelemetryDao telemetryDao;
    @Resource
    private TelemetryToMapService telemetryToMapService;

    @Resource
    private MyDateUtils dateUtils;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm");


    @Override
    public BaseResponse insertTelemetry(Telemetry telemetryRequest) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            if (telemetryRequest != null) {

                telemetryDao.insert(telemetryRequest);
                log.info("新增遥测：{} ", telemetryRequest);
            }
        } catch (Exception e) {
            log.error("新增遥测-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;

    }

    @Override
    public BaseResponse telemetryRealTime() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<Telemetry> telemetryList = new ArrayList<>();
            HashMap<String, Telemetry> hashMap = telemetryToMapService.telemetryReal();
            for (Map.Entry<String, Telemetry> entry : hashMap.entrySet()) {
                telemetryList.add(entry.getValue());
            }
            response.setData(telemetryList);
        } catch (Exception e) {
            log.error("实时遥测-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    /**
     * 遥测回放
     * 7天以内数据约60万按秒回放
     * 7天-1个月约250万数据按分钟回放
     * 1个月-12个月按小时回放
     * 1年以上按每2小时回放
     */
    @Override
    public BaseResponse telemetryHistory(String equipment_id, String telemetry_name, String startTime1, String endTime1) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<Telemetry> telemetryList = new ArrayList<>();
            Date startTime = format.parse(startTime1);
            Date endTime = format.parse(endTime1);

            Integer minute=dateUtils.getBetweenMinutes(startTime,endTime);
            if (minute<=10080) {
                /**
                 * 7天以内按秒回放
                 * */
                telemetryList = telemetryDao.SelectByIdAndTime(equipment_id, telemetry_name, startTime, endTime);
            }else if(minute>10080&&minute<=43200){
                /**
                 * 7天-1个月按分钟回放
                 * */
                telemetryList = telemetryDao.SelectByMinute(equipment_id, telemetry_name, startTime, endTime);
            }else if(minute>43200&&minute<=518400){
                /**
                 * 1个月-12个月按小时回放
                 * */
                telemetryList = telemetryDao.SelectByHour(equipment_id, telemetry_name, startTime, endTime);
            }else if(minute>518400){
                /**
                 * 1年以上按每2小时回放
                 * */
                telemetryList = telemetryDao.SelectBy2Hour(equipment_id, telemetry_name, startTime, endTime);
            }

            for(Telemetry t:telemetryList){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(t.getTime());
                long localOffset = -(calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET));
                double dd = (double) (t.getTime().getTime() - localOffset) / 24 / 3600 / 1000 + 25569.0000000;
                //先默认保留10位小数
                DecimalFormat df = new DecimalFormat("#.0000000000");
                Double dataTime=Double.valueOf(df.format(dd));
                t.setDateTime(dataTime);
            }
            response.setData(telemetryList);
        } catch (Exception e) {
            log.error("遥测回放-实际的业务实现逻辑-发生异常：", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

}
