package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.model.sys.SysUser;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.dao.sys.SysUserDao;
import com.lwc.test.service.sys.impl.security.SecurityUserDetailsServiceImpl;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserReqVO, SysUserRespVO> implements SysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SecurityUserDetailsServiceImpl securityUserDetailsService;

    @Override
    public SysUserRespVO getCurrentUser(SysUserReqVO userReqVO) {
        SysUserRespVO loginUser = null;
        String token = securityUserDetailsService.getTokenByString(userReqVO.getToken());
        if(token.startsWith(token)){
            token = token.substring(token.length());
        }
        if(redisTemplate.hasKey(token)){
            loginUser = (SysUserRespVO)redisTemplate.opsForValue().get(token);
        }
        return loginUser;
    }

    @Override
    @Resource(name = "sysUserDao")
    public void setBaseDao(BaseDao<SysUser,SysUserReqVO, SysUserRespVO> baseDao) {
        super.setBaseDao(baseDao);
    }
}
