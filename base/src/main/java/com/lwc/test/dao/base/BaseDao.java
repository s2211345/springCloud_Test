package com.lwc.test.dao.base;

import com.lwc.test.model.base.BaseModel;
import com.lwc.test.view.base.request.BaseRequestView;
import com.lwc.test.view.base.response.BaseResponseView;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao服务
 */
public interface BaseDao<T extends BaseModel,REQ,RESP> {
    //增删改查
    int save(T record);
    RESP queryById(Integer id);
    int update(T record);
    int delete(Integer id);
    //批量操作
    int batchSave(List<T> lists);
    int batchDelete(List<Integer> ids);

    //分页查询
    int countByReq(REQ dto);
    List<RESP> listByReq(REQ dto);

    //查询
    RESP queryByReq(REQ record);
    int countListByReq(REQ dto);

    //查询所有父菜单
    List<RESP> listParents();
}
