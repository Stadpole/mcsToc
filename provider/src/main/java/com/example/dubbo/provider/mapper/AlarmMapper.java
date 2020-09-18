package com.example.dubbo.provider.mapper;

import com.example.dubbo.provider.entity.Alarm;
import com.example.dubbo.provider.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Stadpole on 2020/6/10 9:21
 */
@Mapper
public interface AlarmMapper {
    @Select("select * from t_alarm")
    List<Alarm> SelecALL();

    @Select("select * from t_alarm where equipment_id like CONCAT('%',#{equipment_id},'%')")
    List<Alarm> SelecEquipemntId(String equipment_id);
    @Select("select * from  t_alarm where time>#{startTime} and time<#{endTime}")
    List<Alarm> SelectByTime(Date startTime,Date endTime);
    @Select("select * from  t_alarm where equipment_id=#{equipment_id} and time>#{startTime} and time<#{endTime}")
    List<Alarm> SelectByIdAndTime(String equipment_id,Date startTime,Date endTime);

}
