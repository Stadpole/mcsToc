package com.example.dubbo.provider.dao;

import com.example.dubbo.api.entity.Telemetry;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (Telemetry)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-24 16:16:14
 */
public interface TelemetryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Telemetry queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Telemetry> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tTelemetryHistory 实例对象
     * @return 对象列表
     */
    List<Telemetry> queryAll(Telemetry tTelemetryHistory);

    /**
     * 新增数据
     *
     * @param tTelemetryHistory 实例对象
     * @return 影响行数
     */
    int insert(Telemetry tTelemetryHistory);

    /**
     * 修改数据
     *
     * @param tTelemetryHistory 实例对象
     * @return 影响行数
     */
    int update(Telemetry tTelemetryHistory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 批量新增数据
     *
     * @param result 实例对象
     * @return 是否成功
     */
    Boolean insertTelemetryBatchCode(List<Telemetry> result);

    /**
     * 按秒查询
     *
     * @param equipment_id 设备id
     * @param telemetry_name 设备名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 查询到的列表
     */
    List<Telemetry> SelectByIdAndTime(String equipment_id, String telemetry_name, Date startTime, Date endTime);
    /**
     * 按每分钟查询
     * @param equipment_id 设备id
     * @param telemetry_name 设备名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 查询到的列表
     */
    List<Telemetry> SelectByMinute(String equipment_id, String telemetry_name,Date startTime, Date endTime);
    /**
     * 按小时查询
     * @param equipment_id 设备id
     * @param telemetry_name 设备名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 查询到的列表
     */
    List<Telemetry> SelectByHour(String equipment_id, String telemetry_name,Date startTime, Date endTime);
    /**
     * 按每两个小时查询
     * @param equipment_id 设备id
     * @param telemetry_name 设备名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 查询到的列表
     */
    List<Telemetry> SelectBy2Hour(String equipment_id, String telemetry_name,Date startTime, Date endTime);
}