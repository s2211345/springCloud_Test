package com.lwc.test.service.base.impl;

import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.model.base.BaseModel;
import com.lwc.test.service.base.BaseService;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    BaseDao<T> dao;
    //通过重写该方法注入BaseDao
    // @Resource("XXDao")
    public void setBaseDao(BaseDao<T> baseDao) {
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
    public T queryById(Integer id){
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
    public int countByReq(T dto) {
        return dao.countByReq(dto);
    }
    @Override
    public List<T> listByReq(T dto) {
        return dao.listByReq(dto);
    }
    //查询
    @Override
    public T queryByReq(T record) {
        return dao.queryByReq(record);
    }
    @Override
    public List<T> queryListByReq(T record) {
        return dao.queryListByReq(record);
    }

    @Override
    public int countListByReq(T dto) {
        return dao.countListByReq(dto);
    }
}
