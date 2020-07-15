package com.lwc.test.dao.sys;

import com.lwc.test.model.sys.SysUserRole;
import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.view.sys.request.SysUserRoleReqVO;
import com.lwc.test.view.sys.response.SysUserRoleRespVO;

/**
 * 用户-角色对应表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:49:58
 */
public interface SysUserRoleDao extends BaseDao<SysUserRole,SysUserRoleReqVO,SysUserRoleRespVO> {
}
