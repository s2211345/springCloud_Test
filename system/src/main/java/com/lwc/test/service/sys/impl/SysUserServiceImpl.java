package com.lwc.test.service.sys.impl;

import com.google.common.collect.Lists;
import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.dao.sys.SysUserRoleDao;
import com.lwc.test.enums.admin.UserStatusEnum;
import com.lwc.test.model.sys.SysUser;
import com.lwc.test.model.sys.SysUserRole;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.service.sys.impl.security.SecurityUserDetailsServiceImpl;
import com.lwc.test.utils.SecurityTokenUtils;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserReqVO, SysUserRespVO> implements SysUserService {
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SecurityUserDetailsServiceImpl securityUserDetailsService;
    @Value("${sys.user.defaultPass}")
    private String defaultPass ;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateUser(SysUserReqVO req) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(req,user);
        user.setId(req.getId());
        user.setUserName(req.getUsername());
        if(ArrayUtils.isNotEmpty(req.getRoles())){
            sysUserRoleDao.delete(req.getId());
            ArrayList<SysUserRole> userRoles = Lists.newArrayList();
            Arrays.stream(req.getRoles()).forEach(role -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(role);
                sysUserRole.setUserId(req.getId());
                userRoles.add(sysUserRole);
            });
            sysUserRoleDao.batchSave(userRoles);
        }
        if(null == user.getId()){
            String encode = passwordEncoder.encode(defaultPass);
            req.setPassword(encode);
            user.setStatus(UserStatusEnum.NORMAL.getCode());
            SysUserRespVO loginUser = securityUserDetailsService.getLoginUser();
            user.setCreateUser(loginUser.getActualName());
            save(user);
        }else{
            update(user);
        }
    }

    @Override
    @Resource(name = "sysUserDao")
    public void setBaseDao(BaseDao<SysUser,SysUserReqVO, SysUserRespVO> baseDao) {
        super.setBaseDao(baseDao);
    }
}
