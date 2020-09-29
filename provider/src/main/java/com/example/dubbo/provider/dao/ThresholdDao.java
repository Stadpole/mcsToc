package com.example.dubbo.provider.dao;

import com.example.dubbo.api.entity.Threshold;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Threshold)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-24 16:16:22
 */
public interface ThresholdDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Threshold queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Threshold> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tThreshold 实例对象
     * @return 对象列表
     */
    List<Threshold> queryAll(Threshold tThreshold);

    /**
     * 新增数据
     *
     * @param tThreshold 实例对象
     * @return 影响行数
     */
    int insert(Threshold tThreshold);

    /**
     * 修改数据
     *
     * @param tThreshold 实例对象
     * @return 影响行数
     */
    int update(Threshold tThreshold);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询所有信息
     *
     * @param
     * @return 对象列表
     */
    List<Threshold> findAll();
    /**
     * 通过dispersed_status查询单条数据
     *
     *  @return 实例对象
     */
    List<Threshold> findByDispersed_status();


}