package com.lwc.test.view.sys.request;

import com.lwc.test.model.base.BaseModel;
import com.lwc.test.view.base.request.BaseRequestView;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * 用户表
 *
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-03 11:03:03
 */
@Data
public class SysUserReqVO extends BaseRequestView implements UserDetails {

	private Long id;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 真实姓名
	private String actualName;
	// 头像
	private String headImgUrl;
	// 手机号码
	private String phone;
	// 电话号码
	private String telePhone;
	// 邮箱
	private String eMail;
	// 生日
	private Date birthday;
	// 性别
	private Boolean sex;
	// 年龄
	private Integer age;
	// 状态 -1 禁用 1正常 -2 删除
	private Integer status;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
	// 创建用户
	private String createUser;

	private List<GrantedAuthority> authoritys;

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authoritys;
	}
}
