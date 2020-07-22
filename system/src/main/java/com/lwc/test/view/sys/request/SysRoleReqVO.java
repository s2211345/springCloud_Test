package com.lwc.test.view.sys.request;

import java.util.Date;
import lombok.Data;
import com.lwc.test.view.base.request.BaseRequestView;


/**
 * 角色表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:47:00
 */
@Data
public class SysRoleReqVO extends BaseRequestView{
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

	// 排序字段
	private String sbyField;
	//排序方式
	private String sby;
	//当前页码
	private int page = 1;
	//当页展示多少条数据
	private int limit = 10;
}
