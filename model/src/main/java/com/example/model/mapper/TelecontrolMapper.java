package com.example.model.mapper;

import com.example.model.entity.Telecontrol;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TelecontrolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Telecontrol record);

    int insertSelective(Telecontrol record);

    Telecontrol selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Telecontrol record);

    int updateByPrimaryKey(Telecontrol record);
}