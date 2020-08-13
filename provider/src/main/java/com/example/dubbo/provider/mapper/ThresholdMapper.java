package com.example.dubbo.provider.mapper;

import com.example.dubbo.provider.entity.Threshold;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Stadpole on 2020/6/10 9:21
 */
@Mapper
public interface ThresholdMapper {
    @Select("select * from t_threshold")
    List<Threshold> SelecALL();
}
