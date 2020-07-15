package com.lwc.test.service.sys;

import com.lwc.test.model.sys.SysUser;
import com.lwc.test.service.base.BaseService;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;

public interface SysUserService extends BaseService<SysUser, SysUserReqVO, SysUserRespVO> {
    /**
     * 获得当前用户
     * @param userReqVO
     * @return
     */
    SysUserRespVO getCurrentUser(SysUserReqVO userReqVO);
}
