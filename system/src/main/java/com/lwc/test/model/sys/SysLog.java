package com.lwc.test.model.sys;

import java.util.Date;
import lombok.Data;
import com.lwc.test.model.base.BaseModel;


/**
 * 用户操作记录
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:25:46
 */
@Data
public class SysLog  extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	// 
	private Integer id;
	// 用户名称
	private String userName;
	// 执行操作
	private String operation;
	// 是否成功
	private Boolean flag;
	// 失败原因
	private String errMsg;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;


}
