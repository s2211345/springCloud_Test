package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.view.sys.request.SysUserRoleReqVO;
import com.lwc.test.view.sys.response.SysUserRoleRespVO;

import com.lwc.test.model.sys.SysUserRole;
import com.lwc.test.service.sys.SysUserRoleService;
import javax.annotation.Resource;


@Service("sysUserRoleService")
@Slf4j
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole,SysUserRoleReqVO, SysUserRoleRespVO> implements SysUserRoleService {
	private static final Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

	@Override
	@Resource(name = "sysUserRoleDao")
	public void setBaseDao(BaseDao<SysUserRole,SysUserRoleReqVO, SysUserRoleRespVO> baseDao) {
		super.setBaseDao(baseDao);
	}
}
