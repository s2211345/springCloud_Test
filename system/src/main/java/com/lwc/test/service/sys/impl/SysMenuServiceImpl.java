package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import com.lwc.test.dao.sys.SysMenuDao;
import com.lwc.test.dao.sys.SysRoleMenuDao;
import com.lwc.test.view.sys.response.SysRoleMenuRespVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.view.sys.request.SysMenuReqVO;
import com.lwc.test.view.sys.response.SysMenuRespVO;

import com.lwc.test.model.sys.SysMenu;
import com.lwc.test.service.sys.SysMenuService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Service("sysMenuService")
@Slf4j
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu,SysMenuReqVO, SysMenuRespVO> implements SysMenuService {

	@Resource
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	public List<SysMenuRespVO> queryListByUserId(Integer userId) {
		return getDao().queryListByUserId(userId);
	}

	@Override
	public List<SysMenuRespVO> listParents() {
		return dao.listParents();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteMenu(Integer id){
		//删除菜单的同时将角色对应的权限删除
		deleteMenuChild(id);
	}

	private void deleteMenuChild(Integer id) {
		SysMenuReqVO menuParam = new SysMenuReqVO();
		menuParam.setParentId(id);
		List<SysMenuRespVO> menus = dao.listByReq(menuParam);
		if(!CollectionUtils.isEmpty(menus) && menus.size() > 0){
			for (SysMenuRespVO menu : menus) {
				deleteMenuChild(menu.getId());
				dao.delete(menu.getId());
				sysRoleMenuDao.deleteByMenuId(menu.getId());
			}
		}
		dao.delete(id);
		sysRoleMenuDao.deleteByMenuId(id);
	}

	private SysMenuDao getDao(){
		return (SysMenuDao) this.dao;
	}

	@Override
	@Resource(name = "sysMenuDao")
	public void setBaseDao(BaseDao<SysMenu,SysMenuReqVO, SysMenuRespVO> baseDao) {
		super.setBaseDao(baseDao);
	}
}
