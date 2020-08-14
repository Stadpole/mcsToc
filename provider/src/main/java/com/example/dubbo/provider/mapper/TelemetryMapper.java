package com.example.dubbo.provider.mapper;

import com.example.dubbo.provider.entity.Telemetry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Stadpole on 2020/6/10 9:21
 */
@Mapper
public interface TelemetryMapper {

    @Insert("<script> insert into t_telemetry_history (equipment_id,telemetry_name,engineering_value,unit,time) values  " +
            "  <foreach collection='result' item='item' separator=',' > " +
            "  (#{item.equipment_id},#{item.telemetry_name},#{item.engineering_value},#{item.unit},#{item.time})\n" +
            "  </foreach> </script>")
    Boolean insertTelemetryBatchCode(@Param(value = "result") List<Telemetry> result);

}
