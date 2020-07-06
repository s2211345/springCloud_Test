package com.lwc.test.dao.base;

import com.lwc.test.model.base.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao服务
 */
public interface BaseDao<T extends BaseModel> {
    //增删改查
    int save(T record);
    T queryById(Integer id);
    int update(T record);
    int delete(Integer id);
    //批量操作
    int batchSave(List<T> lists);
    int batchDelete(List<Integer> ids);

    //分页查询
    int countByReq(T dto);
    List<T> listByReq(T dto);

    //查询
    T queryByReq(T record);
    List<T> queryListByReq(T record);
    int countListByReq(T dto);

}
