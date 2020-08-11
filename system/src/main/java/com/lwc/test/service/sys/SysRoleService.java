package com.lwc.test.service.sys;

import com.lwc.test.model.sys.SysRole;
import com.lwc.test.service.base.BaseService;
import com.lwc.test.view.sys.request.SysRoleReqVO;
import com.lwc.test.view.sys.response.SysRoleRespVO;

/**
 * 角色表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:47:00
 */
public interface SysRoleService extends BaseService<SysRole,SysRoleReqVO,SysRoleRespVO>{
    void saveOrUpdate(SysRoleReqVO req);
}
