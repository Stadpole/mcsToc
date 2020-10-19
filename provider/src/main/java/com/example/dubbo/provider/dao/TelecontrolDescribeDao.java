package com.example.dubbo.provider.dao;

import com.example.dubbo.api.entity.TelecontrolDescribe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TTelecontrolDescribe)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-28 18:09:13
 */
public interface TelecontrolDescribeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param equipmentId 主键
     * @return 实例对象
     */
    TelecontrolDescribe queryById(String equipmentId,String commandNumber);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TelecontrolDescribe> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tTelecontrolDescribe 实例对象
     * @return 对象列表
     */
    List<TelecontrolDescribe> queryAll(TelecontrolDescribe tTelecontrolDescribe);

    /**
     * 新增数据
     *
     * @param tTelecontrolDescribe 实例对象
     * @return 影响行数
     */
    int insert(TelecontrolDescribe tTelecontrolDescribe);

    /**
     * 修改数据
     *
     * @param tTelecontrolDescribe 实例对象
     * @return 影响行数
     */
    int update(TelecontrolDescribe tTelecontrolDescribe);

    /**
     * 通过主键删除数据
     *
     * @param equipmentId 主键
     * @return 影响行数
     */
    int deleteById(String equipmentId);

}