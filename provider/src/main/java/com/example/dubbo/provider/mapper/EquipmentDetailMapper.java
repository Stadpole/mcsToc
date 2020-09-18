package com.example.dubbo.provider.mapper;

import com.example.dubbo.provider.entity.EquipmentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Stadpole on 2020/6/10 9:21
 */
@Mapper
public interface EquipmentDetailMapper {
    @Select("select * from t_equipment_detail where equipment_id={#equipment_id}")
    EquipmentDetail listEquipmentDetail(String equipment_id);


}
