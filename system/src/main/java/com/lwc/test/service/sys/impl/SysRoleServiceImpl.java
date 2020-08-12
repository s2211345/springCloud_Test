package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.dao.sys.SysRoleMenuDao;
import com.lwc.test.dao.sys.SysUserRoleDao;
import com.lwc.test.model.sys.SysRoleMenu;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.view.sys.request.SysRoleReqVO;
import com.lwc.test.view.sys.response.SysRoleRespVO;

import com.lwc.test.model.sys.SysRole;
import com.lwc.test.service.sys.SysRoleService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service("sysRoleService")
@Slf4j
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole,SysRoleReqVO, SysRoleRespVO> implements SysRoleService {

	@Resource
	SysRoleMenuDao roleMenuDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(SysRoleReqVO req) {
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(req,sysRole);
		if(null == req.getId()){
			save(sysRole);
		}else{
			update(sysRole);
		}
		if(null != req.getRoleList() && req.getRoleList().length > 0){
			Arrays.sort(req.getRoleList());
			roleMenuDao.deleteByRoleId(req.getId());
			List<SysRoleMenu> roleMenuList = new ArrayList<>();
			for (int i = 0; i < req.getRoleList().length; i++) {
				SysRoleMenu sysRoleMenu = new SysRoleMenu();
				sysRoleMenu.setRoleId(sysRole.getId());
				sysRoleMenu.setMenuId(req.getRoleList()[i]);
				roleMenuList.add(sysRoleMenu);
			}
			roleMenuDao.batchSave(roleMenuList);
		}
	}

	@Override
	@Resource(name = "sysRoleDao")
	public void setBaseDao(BaseDao<SysRole,SysRoleReqVO, SysRoleRespVO> baseDao) {
		super.setBaseDao(baseDao);
	}
}
