package com.lwc.test.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础Model服务
 */
@Data
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    // 状态 -1 禁用 1正常 -2 删除
    private Integer status;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

}
