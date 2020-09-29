package com.example.dubbo.provider.dao;

import com.example.dubbo.api.entity.TTelecontrol;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TTelecontrol)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-24 16:16:05
 */
public interface TTelecontrolDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TTelecontrol queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TTelecontrol> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tTelecontrol 实例对象
     * @return 对象列表
     */
    List<TTelecontrol> queryAll(TTelecontrol tTelecontrol);

    /**
     * 新增数据
     *
     * @param tTelecontrol 实例对象
     * @return 影响行数
     */
    int insert(TTelecontrol tTelecontrol);

    /**
     * 修改数据
     *
     * @param tTelecontrol 实例对象
     * @return 影响行数
     */
    int update(TTelecontrol tTelecontrol);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}