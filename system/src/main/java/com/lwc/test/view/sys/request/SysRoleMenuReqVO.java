package com.lwc.test.view.sys.request;

import java.util.Date;
import lombok.Data;
import com.lwc.test.view.base.request.BaseRequestView;


/**
 * 角色权限表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:48:51
 */
@Data
public class SysRoleMenuReqVO extends BaseRequestView{
	private static final long serialVersionUID = 1L;

	// 角色id
	private Integer roleId;
	// 菜单id
	private Integer menu;

	private int page = 1;  //当前页码
	private int limit = 10; //当页展示多少条数据
}
