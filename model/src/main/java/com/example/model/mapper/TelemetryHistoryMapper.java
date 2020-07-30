package com.example.model.mapper;

import com.example.model.entity.TelemetryHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TelemetryHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TelemetryHistory record);

    int insertSelective(TelemetryHistory record);

    TelemetryHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TelemetryHistory record);

    int updateByPrimaryKey(TelemetryHistory record);
}