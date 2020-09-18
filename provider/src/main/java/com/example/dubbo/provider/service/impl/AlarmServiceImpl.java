package com.example.dubbo.provider.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.api.common.enums.StatusCode;
import com.example.dubbo.api.common.request.AlarmRequest;
import com.example.dubbo.api.common.response.BaseResponse;
import com.example.dubbo.api.service.AlarmService;
import com.example.dubbo.provider.common.BeanUtills;
import com.example.dubbo.provider.entity.Alarm;
import com.example.dubbo.provider.entity.UserInfo;
import com.example.dubbo.provider.mapper.AlarmMapper;
import com.example.dubbo.provider.repository.AlarmRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Stadpole on 2020/8/21.
 */
//@Service(protocol = {"dubbo", "rest"}, validation = "true", version = "1.0.0", timeout = 3000)
@Service(version = "1.0.0")
@Slf4j
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;
    @Autowired
    private AlarmMapper alarmMapper;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Override
    public BaseResponse pageAlarm(Integer page, Integer size,String equipment_id, String startTime1, String endTime1) {
        //TODO:分页组件-第pageNo页，pageSize条数目数据
        PageHelper.startPage(page,size);
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            List<Alarm> alarms=null;
            Date startTime=format.parse(startTime1);
            Date endTime=format.parse(endTime1);

            if(StringUtils.isNotBlank(equipment_id)&&startTime!=null&&endTime!=null){
                alarms= alarmMapper.SelectByIdAndTime(equipment_id,startTime,endTime);
            }
            else if(StringUtils.isNotBlank(equipment_id)&&startTime==null&&endTime==null){
                alarms= alarmMapper.SelecEquipemntId(equipment_id);
            }
            else if(!StringUtils.isNotBlank(equipment_id)&&startTime!=null&&endTime!=null){
                alarms= alarmMapper.SelectByTime(startTime,endTime);
            }
            else{
                alarms = alarmMapper.SelecALL();
            }
            PageInfo<UserInfo> pageInfo = new PageInfo(alarms);

            response.setData(pageInfo);

        }catch (Exception e){
            log.error("告警日志分页查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }


    @Override
    public BaseResponse updateAlarm(AlarmRequest alarmRequest) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if(alarmRequest.getId()!=null) {
                Alarm entity=alarmRepository.findById(alarmRequest.getId());
                BeanUtills.copyProperties(alarmRequest, entity);
                alarmRepository.save(entity);
                log.info("更新告警日志：{} ", entity);
            }

        }catch (Exception e){
            log.error("更新告警日志-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

}