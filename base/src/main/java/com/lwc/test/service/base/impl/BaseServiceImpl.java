package com.lwc.test.service.base.impl;

import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.model.base.BaseModel;
import com.lwc.test.service.base.BaseService;
import com.lwc.test.view.base.request.BaseRequestView;
import com.lwc.test.view.base.response.BaseResponseView;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T extends BaseModel,REQ,RESP> implements BaseService<T,REQ,RESP> {

    public BaseDao<T,REQ,RESP> dao;
    // 注入Dao
    // @Resource("XXDao")
    public void setBaseDao(BaseDao<T,REQ,RESP> baseDao) {
        this.dao = baseDao;
    }

    //增删改查
    @Override
    public int save(T record){
        Date date = new Date();
        record.setCreateTime(date);
        return dao.save(record);
    }
    @Override
    public RESP queryById(Integer id){
        return dao.queryById(id);
    }
    @Override
    public int update(T record){
        Date date = new Date();
        record.setUpdateTime(date);
        return dao.update(record);
    }
    @Override
    public int delete(Integer id){
        return dao.delete(id);
    }
    //批量操作
    @Override
    public int batchSave(List<T> lists){
        Date date = new Date();
        for (int i = 0; i < lists.size(); i++) {
            T t = lists.get(i);
            t.setCreateTime(date);
        }
        return dao.batchSave(lists);
    }
    @Override
    public int batchDelete(List<Integer> ids){
        return dao.batchDelete(ids);
    }

    //分页查询
    @Override
    public int countByReq(REQ dto) {
        return dao.countByReq(dto);
    }
    @Override
    public List<RESP> listByReq(REQ dto) {
        return dao.listByReq(dto);
    }
    //查询
    @Override
    public RESP queryByReq(REQ record) {
        return dao.queryByReq(record);
    }
}
