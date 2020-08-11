package com.lwc.test.view.sys.response;

import java.util.Date;
import lombok.Data;
import com.lwc.test.view.base.response.BaseResponseView;


/**
 * 角色权限表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:48:51
 */
@Data
public class SysRoleMenuRespVO extends BaseResponseView{
	private static final long serialVersionUID = 1L;
	
	// 角色id
	private Integer roleId;
	// 菜单id
	private Integer menuId;
}
