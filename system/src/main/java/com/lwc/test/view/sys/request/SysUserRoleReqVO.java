package com.lwc.test.view.sys.request;

import java.util.Date;
import lombok.Data;
import com.lwc.test.view.base.request.BaseRequestView;


/**
 * 用户-角色对应表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:49:58
 */
@Data
public class SysUserRoleReqVO extends BaseRequestView{
	private static final long serialVersionUID = 1L;

	// 用户id
	private Integer userId;
	// 角色id
	private Integer roleId;

	private int page = 1;  //当前页码
	private int limit = 10; //当页展示多少条数据
}
