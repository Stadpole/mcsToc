package com.example.dubbo.provider.dao;

import com.example.dubbo.provider.entity.TEquipmentDetail;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (EquipmentDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-24 16:15:38
 */
public interface EquipmentDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param equipmentId 主键
     * @return 实例对象
     */
    TEquipmentDetail queryById(String equipmentId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TEquipmentDetail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tEquipmentDetail 实例对象
     * @return 对象列表
     */
    List<TEquipmentDetail> queryAll(TEquipmentDetail tEquipmentDetail);

    /**
     * 新增数据
     *
     * @param tEquipmentDetail 实例对象
     * @return 影响行数
     */
    int insert(TEquipmentDetail tEquipmentDetail);

    /**
     * 修改数据
     *
     * @param tEquipmentDetail 实例对象
     * @return 影响行数
     */
    int update(TEquipmentDetail tEquipmentDetail);

    /**
     * 通过主键删除数据
     *
     * @param equipmentId 主键
     * @return 影响行数
     */
    int deleteById(String equipmentId);

}