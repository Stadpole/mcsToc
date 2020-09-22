package com.example.dubbo.provider.mapper;


import com.example.dubbo.provider.entity.UserInfo;
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
    void insert(@Param(value = "result") List<UserInfo> result);

    @Select("select id,username,role,description from t_user where role like CONCAT('%',#{role},'%')")
    List<UserInfo> SelectRole(String role);
    @Select("select * from t_user where job like CONCAT('%',#{job},'%')")
    List<UserInfo> SelectJob(String job);
    @Select("select * from t_user where role like CONCAT('%',#{role},'%') and job like CONCAT('%',#{job},'%')")
    List<UserInfo> SelectRoleAndJob(String role,String job);
    @Select("select id,username,role,description from t_user")
    List<UserInfo> SelecALL();
}
