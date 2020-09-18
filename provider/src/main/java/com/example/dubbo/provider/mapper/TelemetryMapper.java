package com.example.dubbo.provider.mapper;

import com.example.dubbo.provider.entity.Telemetry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
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

    @Select("select engineering_value, time from t_telemetry_history where equipment_id=#{equipment_id} and telemetry_name=#{telemetry_name} and time>#{startTime} and time<#{endTime}")
    List<Telemetry> SelectByIdAndTime(String equipment_id,String telemetry_name, Date startTime, Date endTime);

    //TODO：回放 按每分钟采一次点
    @Select("SELECT\n" +
            "  engineering_value,time\n" +
            "FROM\n" +
            "  t_telemetry_history\n" +
            "WHERE\n" +
            "  id in(\n" +
            "    SELECT\n" +
            "      max(id)\n" +
            "    FROM\n" +
            "      t_telemetry_history\n" +
            "    WHERE\n" +
            "\t\t equipment_id=#{equipment_id} AND\n" +
            "\t\t telemetry_name=#{telemetry_name} AND\n" +
            "\t\t unix_timestamp(time) BETWEEN unix_timestamp(#{startTime})\n" +
            "      AND unix_timestamp(#{endTime})\n" +
            "    GROUP BY\n" +
            "\t\t CEILING((unix_timestamp(time)/60))\n" +
            "  )\n" +
            "ORDER BY\n" +
            "  time;")
    List<Telemetry> SelectByMinute(String equipment_id, String telemetry_name,Date startTime, Date endTime);
}
