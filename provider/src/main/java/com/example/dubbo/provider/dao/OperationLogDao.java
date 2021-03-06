package com.example.dubbo.provider.dao;

import com.example.dubbo.api.entity.OperationLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (OperationLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-24 16:15:49
 */
public interface OperationLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OperationLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OperationLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * * @return 对象列表
     */
    List<OperationLog> queryAll(@Param("userId") Integer userId, @Param("method") String method,@Param("startTime") Long startTime,@Param("endTime") Long endTime);

    /**
     * 新增数据
     *
     * @param tOperationLog 实例对象
     * @return 影响行数
     */
    int insert(OperationLog tOperationLog);

    /**
     * 修改数据
     *
     * @param tOperationLog 实例对象
     * @return 影响行数
     */
    int update(OperationLog tOperationLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}