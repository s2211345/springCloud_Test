package com.lwc.test.service.sys;

import com.lwc.test.model.sys.SysUser;
import com.lwc.test.service.base.BaseService;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;

public interface SysUserService extends BaseService<SysUser, SysUserReqVO, SysUserRespVO> {
    void saveOrUpdateUser(SysUserReqVO req);

}
