package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.view.sys.request.SysRoleReqVO;
import com.lwc.test.view.sys.response.SysRoleRespVO;

import com.lwc.test.model.sys.SysRole;
import com.lwc.test.service.sys.SysRoleService;
import javax.annotation.Resource;


@Service("sysRoleService")
@Slf4j
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole,SysRoleReqVO, SysRoleRespVO> implements SysRoleService {
	private static final Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

	@Override
	@Resource(name = "sysRoleDao")
	public void setBaseDao(BaseDao<SysRole,SysRoleReqVO, SysRoleRespVO> baseDao) {
		super.setBaseDao(baseDao);
	}
}
