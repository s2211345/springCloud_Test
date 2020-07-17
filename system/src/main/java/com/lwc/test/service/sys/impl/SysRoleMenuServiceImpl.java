package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.view.sys.request.SysRoleMenuReqVO;
import com.lwc.test.view.sys.response.SysRoleMenuRespVO;

import com.lwc.test.model.sys.SysRoleMenu;
import com.lwc.test.service.sys.SysRoleMenuService;
import javax.annotation.Resource;


@Service("sysRoleMenuService")
@Slf4j
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu,SysRoleMenuReqVO, SysRoleMenuRespVO> implements SysRoleMenuService {

	@Override
	@Resource(name = "sysRoleMenuDao")
	public void setBaseDao(BaseDao<SysRoleMenu,SysRoleMenuReqVO, SysRoleMenuRespVO> baseDao) {
		super.setBaseDao(baseDao);
	}
}
