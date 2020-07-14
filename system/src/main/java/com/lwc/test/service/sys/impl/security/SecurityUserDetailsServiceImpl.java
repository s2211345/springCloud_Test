package com.lwc.test.service.sys.impl.security;

import com.lwc.test.controller.sys.LoginUser;
import com.lwc.test.dao.sys.SysUserDao;
import com.lwc.test.enums.base.BaseStatusCodeEnum;
import com.lwc.test.model.sys.SysUser;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysUserService sysUserService;

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
        String genCaptcha = (String) request.getSession().getAttribute("index_code");
        if (StringUtils.isEmpty(requestCaptcha)) {
            throw new AuthenticationServiceException("验证码不能为空!");
        }
        if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
            throw new AuthenticationServiceException("验证码错误!");
        }
        SysUser queryParam = new SysUser();
        queryParam.setUserName(username);
        SysUser user = sysUserService.queryByReq(queryParam);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if (BaseStatusCodeEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedException("用户被锁定,请联系管理员");
        } else if (BaseStatusCodeEnum.DELETE.getCode().equals(user.getStatus())) {
            throw new DisabledException("用户已作废");
        }
        LoginUser loginUser = new LoginUser();
        try {
            ReflectUtils.copySameFieldToTargetFilterNull(user,loginUser);
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return loginUser;
    }
}
