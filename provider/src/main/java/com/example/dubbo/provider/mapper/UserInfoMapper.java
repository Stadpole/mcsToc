package com.example.dubbo.provider.mapper;

import com.example.dubbo.provider.entity.Equipment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Stadpole on 2020/6/10 9:21
 */
@Mapper
public interface UserInfoMapper {
    @Insert("<script> insert into t_user (username,password,name,phone_number,email,role,job) values  " +
            " (#{username},#{password},#{item.description},#{item.raw},#{item.engineer},#{item.time})</script>")
    Boolean insert(@Param(value = "result") List<Equipment> result);


}
