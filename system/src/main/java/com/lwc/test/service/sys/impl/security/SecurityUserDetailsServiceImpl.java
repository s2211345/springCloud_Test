package com.lwc.test.service.sys.impl.security;

import com.lwc.test.controller.sys.SysLogController;
import com.lwc.test.enums.base.BaseStatusCodeEnum;
import com.lwc.test.model.sys.SysLog;
import com.lwc.test.service.sys.SysLogService;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.utils.AESUtils;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    RedisTemplate redisTemplate;
    @Value("${token.expire.seconds}")
    private Integer expireSeconds;

    private static final String tokenHead = "Bearer ";
    private static final String tokenHeader = "Authorization";

    private String tokenPrefix = "sys_token:";

    /**
     * 检测用户名是否存在和用户状态是否正常，验证验证码，返回UserDetails信息
     * 密码Security会有默认的AuthenticationProvider处理是否与返回对象信息一致
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 验证码验证
        String requestCaptcha = request.getParameter("code");
        String genCaptcha = (String) request.getSession().getAttribute("index_login_code");
        if (StringUtils.isBlank(requestCaptcha)) {
            throw new AuthenticationServiceException("验证码不能为空!");
        }
        if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
            throw new AuthenticationServiceException("验证码错误!");
        }
        SysUserReqVO queryParam = new SysUserReqVO();
        queryParam.setUserName(username);
        SysUserRespVO user = sysUserService.queryByReq(queryParam);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if (BaseStatusCodeEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedException("用户被锁定,请联系管理员");
        } else if (BaseStatusCodeEnum.DELETE.getCode().equals(user.getStatus())) {
            throw new DisabledException("用户已作废");
        }
        return user;
    }

    /**
     * 获得token并缓存用户信息
     * @param loginUser
     * @return
     */
    public String getAndSaveToken(SysUserRespVO loginUser) {
        String token = AESUtils.encrypt(loginUser.getUsername());
        loginUser.setToken(token);
        saveSysUser(loginUser);
        // 登录日志
        SysLog sysLog = new SysLog();
        sysLog.setUserId(loginUser.getId());
        sysLog.setOperation("登录");
        sysLog.setFlag(true);
        sysLog.setCreateTime(new Date());
        sysLogService.save(sysLog);
        return token;
    }

    /**
     * 保存用户信息到Redis
     * @param loginUser
     */
    private void saveSysUser(SysUserRespVO loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
        log.info(loginUser.getLoginTime() + " : " + loginUser.getCreateUser() + "登录");
        // 缓存
        redisTemplate.boundValueOps(getTokenKey(loginUser.getToken())).set(loginUser, expireSeconds, TimeUnit.SECONDS);
    }

    private String getTokenKey(String token) {
        return tokenPrefix + token;
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getParameter(tokenHeader);
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(tokenHeader);
        }
        if(token.startsWith(tokenHead)){
            token = token.substring(tokenHead.length());
        }

        return token;
    }

    public String getTokenByString(String token) {
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(tokenHeader);
        }
        if(token.startsWith(tokenHead)){
            token = token.substring(tokenHead.length());
        }
        return token;
    }

    public boolean userOutLogin(String token){
        String key = getTokenKey(token);
        SysUserRespVO loginUser = (SysUserRespVO)redisTemplate.opsForValue().get(key);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            // 退出日志
            SysLog sysLog = new SysLog();
            sysLog.setUserId(loginUser.getId());
            sysLog.setOperation("退出");
            sysLog.setFlag(true);
            sysLog.setCreateTime(new Date());
            sysLogService.save(sysLog);
            return true;
        }

        return false;
    }
}
