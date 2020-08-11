package com.lwc.test.model.sys;

import java.util.Collection;
import java.util.Date;
import com.lwc.test.model.base.BaseModel;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


/**
 * 用户表
 *
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-03 11:03:03
 */
@Data
public class SysUser extends BaseModel{
	private static final long serialVersionUID = 1L;
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
	private String mailbox;
	// 生日
	private Date birthday;
	// 性别
	private Boolean sex;
	// 年龄
	private Integer age;
	// 创建用户
	private String createUser;
}
