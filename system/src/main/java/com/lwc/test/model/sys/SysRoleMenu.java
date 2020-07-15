package com.lwc.test.model.sys;

import java.util.Date;
import lombok.Data;
import com.lwc.test.model.base.BaseModel;


/**
 * 角色权限表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:48:51
 */
@Data
public class SysRoleMenu  extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	// 角色id
	private Integer roleId;
	// 菜单id
	private Integer menu;


}
