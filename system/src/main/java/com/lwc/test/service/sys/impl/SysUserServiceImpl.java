package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.model.sys.SysUser;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserReqVO, SysUserRespVO> implements SysUserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void saveUser(SysUserReqVO req) {
        String encode = passwordEncoder.encode(req.getPassword());
        req.setPassword(encode);
        SysUser user = new SysUser();
        user.setUserName(req.getUsername());
        BeanUtils.copyProperties(req,user);
        save(user);
    }

    @Override
    @Resource(name = "sysUserDao")
    public void setBaseDao(BaseDao<SysUser,SysUserReqVO, SysUserRespVO> baseDao) {
        super.setBaseDao(baseDao);
    }
}
