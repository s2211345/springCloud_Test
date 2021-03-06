package com.lwc.test.service.sys;

import com.lwc.test.model.sys.SysMenu;
import com.lwc.test.service.base.BaseService;
import com.lwc.test.view.sys.request.SysMenuReqVO;
import com.lwc.test.view.sys.response.SysMenuRespVO;

import java.util.List;

/**
 * 菜单表
 * 
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:32:36
 */
public interface SysMenuService extends BaseService<SysMenu,SysMenuReqVO,SysMenuRespVO>{
    /**
     * 根据用户Id查询菜单权限
     * @param userId
     * @return
     */
    List<SysMenuRespVO> queryListByUserId(Integer userId);

    List<SysMenuRespVO> listParents();

    void deleteMenu(Integer id);
}
