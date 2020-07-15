package com.lwc.test.model.sys;

import java.util.Date;
import lombok.Data;
import com.lwc.test.model.base.BaseModel;


/**
 * 用户-角色对应表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:49:58
 */
@Data
public class SysUserRole  extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	// 用户id
	private Integer userId;
	// 角色id
	private Integer roleId;


}
