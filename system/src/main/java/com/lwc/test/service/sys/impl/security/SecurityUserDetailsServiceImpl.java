package com.lwc.test.service.sys.impl.security;

import com.lwc.test.controller.sys.SysLogController;
import com.lwc.test.enums.base.BaseStatusCodeEnum;
import com.lwc.test.model.sys.SysLog;
import com.lwc.test.model.sys.security.AccessToken;
import com.lwc.test.service.sys.SysLogService;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.utils.AESUtils;
import com.lwc.test.utils.DateUtils;
import com.lwc.test.utils.SecurityTokenUtils;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private SecurityTokenUtils securityTokenUtils;
    
    @Value("${token.expire.seconds}")
    private Integer expireSeconds;

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
        //如果带有token并且是有效的跳过code验证 / 给需要获得用户信息和权限的方法使用
        Boolean isLogin = true;
        String token = securityTokenUtils.getTokenByRequest(request);
        if (StringUtils.isNotBlank(token)) {
            String userName = securityTokenUtils.getSubjectFromToken(token);
            SysUserRespVO userRespVO = new SysUserRespVO();
            userRespVO.setUserName(userName);
            if(securityTokenUtils.validateToken(token,userRespVO)){
                isLogin = false;
            }
        }
        if(isLogin) {
            String requestCaptcha = request.getParameter("code");
            String genCaptcha = (String) request.getSession().getAttribute("index_login_code");
            if (StringUtils.isBlank(requestCaptcha)) {
                throw new AuthenticationServiceException("验证码不能为空!");
            }
            if (StringUtils.isBlank(genCaptcha)) {
                throw new AuthenticationServiceException("验证码已过期，请刷新验证码!");
            }
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                throw new AuthenticationServiceException("验证码错误!");
            }
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
    public AccessToken getAndSaveToken(UserDetails loginUser) {
        AccessToken token = securityTokenUtils.createToken(loginUser);
        securityTokenUtils.setCacheUserByToken(loginUser,token.getToken());
        // 登录日志
        SysLog sysLog = new SysLog();
        sysLog.setUserName(loginUser.getUsername());
        sysLog.setOperation("登录");
        sysLog.setFlag(true);
        sysLog.setCreateTime(new Date());
        sysLogService.save(sysLog);
        return token;
    }

    public boolean userOutLogin(String token){
        SysUserRespVO userRespVO = new SysUserRespVO();
        userRespVO.setUserName(securityTokenUtils.getSubjectFromToken(token));
        if(securityTokenUtils.validateToken(token,userRespVO)){
            securityTokenUtils.addTokenBlacklist(token);
            UserDetails user = securityTokenUtils.getCacheUserByToken(token);
            // 退出日志
            SysLog sysLog = new SysLog();
            sysLog.setUserName(user.getUsername());
            sysLog.setOperation("退出");
            sysLog.setFlag(true);
            sysLog.setCreateTime(new Date());
            sysLogService.save(sysLog);
            return true;
        }
        return false;
    }

    /**
     * 从上下文中取得认证对象
     * @return
     */
    public SysUserRespVO getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return (SysUserRespVO) authentication.getPrincipal();
            }
        }
        return null;
    }
}
