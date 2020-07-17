package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.view.sys.request.SysMenuReqVO;
import com.lwc.test.view.sys.response.SysMenuRespVO;

import com.lwc.test.model.sys.SysMenu;
import com.lwc.test.service.sys.SysMenuService;
import javax.annotation.Resource;


@Service("sysMenuService")
@Slf4j
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu,SysMenuReqVO, SysMenuRespVO> implements SysMenuService {

	@Override
	@Resource(name = "sysMenuDao")
	public void setBaseDao(BaseDao<SysMenu,SysMenuReqVO, SysMenuRespVO> baseDao) {
		super.setBaseDao(baseDao);
	}
}
