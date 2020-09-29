package com.example.dubbo.provider.dao;

import com.example.dubbo.api.entity.Alarm;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (Alarm)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-24 16:14:11
 */
public interface AlarmDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Alarm queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Alarm> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param equipment_id 设备id
     * @return 对象列表
     */
    List<Alarm> queryAll(String equipment_id,Date startTime,Date endTime);

    /**
     * 新增数据
     *
     * @param alarm 实例对象
     * @return 影响行数
     */
    int insert(Alarm alarm);

    /**
     * 修改数据
     *
     * @param alarm 实例对象
     * @return 影响行数
     */
    int update(Alarm alarm);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}