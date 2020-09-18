package com.example.dubbo.provider.mapper;

import com.example.dubbo.provider.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Stadpole on 2020/6/10 9:21
 */
@Mapper
public interface OperationLogMapper {
    @Select("select * from t_operation_log")
    List<OperationLog> SelecALL();


}
