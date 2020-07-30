package com.example.model.mapper;

import com.example.model.entity.WarningLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarningLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarningLog record);

    int insertSelective(WarningLog record);

    WarningLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningLog record);

    int updateByPrimaryKey(WarningLog record);
}