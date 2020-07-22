package com.lwc.test.model.sys;

import java.util.Date;
import java.util.List;

import lombok.Data;
import com.lwc.test.model.base.BaseModel;


/**
 * 菜单表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:32:36
 */
@Data
public class SysMenu  extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	// 
	private Integer id;
	// 父id
	private Integer parentId;
	// 名称
	private String name;
	// 图标
	private String ico;
	// 链接
	private String href;
	// 类型 1 菜单 2按钮
	private Integer type;
	// 权限
	private String permission;
	// 排序
	private Integer sort;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
	// 创建用户
	private Integer createUser;


}
