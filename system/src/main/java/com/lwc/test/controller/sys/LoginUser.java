package com.lwc.test.controller.sys;

import com.lwc.test.enums.base.BaseStatusCodeEnum;
import com.lwc.test.model.sys.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class LoginUser extends SysUser implements UserDetails {
    private static final long serialVersionUID = -1379274258881257107L;

    private String token;
    /** 登陆时间戳（毫秒） */
    private Long loginTime;
    /** 过期时间戳 */
    private Long expireTime;
    private List<GrantedAuthority> authoritys;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritys;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ! BaseStatusCodeEnum.DISABLE.getCode().equals(getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
