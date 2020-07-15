package com.lwc.test.view.sys.response;

import java.util.Date;
import lombok.Data;
import com.lwc.test.view.base.response.BaseResponseView;


/**
 * 角色表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:47:00
 */
@Data
public class SysRoleRespVO extends BaseResponseView{
	private static final long serialVersionUID = 1L;
	
	// 
	private Integer id;
	// 角色名称
	private String name;
	// 描述
	private String description;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
}
