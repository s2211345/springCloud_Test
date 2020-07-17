package com.lwc.test.service.sys.impl;

import com.lwc.test.dao.base.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lwc.test.service.base.impl.BaseServiceImpl;
import com.lwc.test.view.sys.request.SysLogReqVO;
import com.lwc.test.view.sys.response.SysLogRespVO;

import com.lwc.test.model.sys.SysLog;
import com.lwc.test.service.sys.SysLogService;
import javax.annotation.Resource;


@Service("sysLogService")
@Slf4j
public class SysLogServiceImpl extends BaseServiceImpl<SysLog,SysLogReqVO, SysLogRespVO> implements SysLogService {

	@Override
	@Resource(name = "sysLogDao")
	public void setBaseDao(BaseDao<SysLog,SysLogReqVO, SysLogRespVO> baseDao) {
		super.setBaseDao(baseDao);
	}
}
