package com.lwc.test.view.sys.request;

import java.util.Date;
import lombok.Data;
import com.lwc.test.view.base.request.BaseRequestView;


/**
 * 用户操作记录
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:25:46
 */
@Data
public class SysLogReqVO extends BaseRequestView{
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 用户id
	private Integer userName;
	// 用户姓名
	private String actualName;
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

	// 排序字段
	private String sbyField;
	//排序方式
	private String sby;
	//当前页码
	private int page = 0;
	//当页展示多少条数据
	private int limit = 10;
}
