package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.model.sys.SysUser;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.dao.sys.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    @Resource(name = "sysUserDao")
    public void setBaseDao(BaseDao<SysUser> baseDao) {
        super.setBaseDao(baseDao);
    }
}
