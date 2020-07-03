package com.lwc.base.dao;

import java.util.List;

/**
 * 基础Dao服务
 */
public interface BaseDao<T> {
    //================== 基本CRUD ==================
    int insertSelective(T record);
    int updateByPrimaryKeySelective(T record);
    T selectByPrimaryKey(Integer id);
    int deleteByPrimaryKey(Integer id);
    //================== 基本CRUD ==================

    //计数
    int count(T dto);
    //获取list
    List<T> list(T dto);

    //批量删除
    int deleteBatchByPrimaryKey(Object[] ids);
    //批量新增
    int insertBatch(List<T> list);
    //根据对象查询对象(只做单表)
    T selectDtoByDto(T record);
    //根据对象查询list(只做单表)


    List<T> selectListByDto(T record);

}
