package com.lwc.test.controller.sys;

import com.google.common.collect.Lists;
import com.lwc.test.service.sys.impl.security.SecurityUserDetailsServiceImpl;
import com.lwc.test.utils.SecurityTokenUtils;
import com.lwc.test.view.base.response.BaseResult;
import com.lwc.test.view.sys.response.SysUserRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lwc.test.service.sys.SysMenuService;
import com.lwc.test.view.sys.response.SysResult;
import com.lwc.test.model.sys.SysMenu;
import com.lwc.test.view.sys.request.SysMenuReqVO;
import com.lwc.test.view.sys.response.SysMenuRespVO;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单表
 *
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:32:36
 */
@Api("菜单表")
@RestController
@RequestMapping("/admin/sysmenu")
@Slf4j
public class SysMenuController{

	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SecurityUserDetailsServiceImpl securityUserDetailsService;

	@ApiOperation(value = "当前登录用户拥有的权限")
	@GetMapping("/current")
	public List<SysMenuRespVO> permissionsCurrent() {
		SysUserRespVO loginUser = securityUserDetailsService.getLoginUser();
		List<SysMenuRespVO> list = loginUser.getAuthoritys();
		final List<SysMenuRespVO> permissions = list.stream().filter(l -> l.getType().equals(1))
				.collect(Collectors.toList());

		List<SysMenuRespVO> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0)).collect(Collectors.toList());
		firstLevel.parallelStream().forEach(p -> {
			setChild(p, permissions);
		});

		return firstLevel;
	}

	/**
	 * 设置子元素
	 *
	 * @param p
	 * @param permissions
	 */
	private void setChild(SysMenuRespVO p, List<SysMenuRespVO> permissions) {
		List<SysMenuRespVO> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
		p.setChild(child);
		if (!CollectionUtils.isEmpty(child)) {
			child.parallelStream().forEach(c -> {
				//递归设置子元素，多级菜单支持
				setChild(c, permissions);
			});
		}
	}

	@GetMapping("/getList")
	@ApiOperation(value = "菜单列表")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public List<SysMenuRespVO> permissionsList() {
		List<SysMenuRespVO> permissionsAll = sysMenuService.listByReq(new SysMenuReqVO());
		List<SysMenuRespVO> list = Lists.newArrayList();
		setPermissionsList(0, permissionsAll, list);

		return list;
	}

	/**
	 * 校验权限
	 *
	 * @return
	 */
	@GetMapping("/owns")
	@ApiOperation(value = "校验当前用户的权限")
	public Set<String> ownsPermission() {
		List<SysMenuRespVO> permissions = securityUserDetailsService.getLoginUser().getAuthoritys();
		if (CollectionUtils.isEmpty(permissions)) {
			return Collections.emptySet();
		}

		return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(SysMenuRespVO::getPermission).collect(Collectors.toSet());
	}

	/**
	 * 菜单列表
	 *
	 * @param pId
	 * @param permissionsAll
	 * @param list
	 */
	private void setPermissionsList(Integer pId, List<SysMenuRespVO> permissionsAll, List<SysMenuRespVO> list) {
		for (SysMenuRespVO per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				list.add(per);
				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					setPermissionsList(per.getId(), permissionsAll, list);
				}
			}
		}
	}

	@GetMapping("/parents")
	@ApiOperation(value = "一级菜单")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public List<SysMenuRespVO> parentMenu() {
		List<SysMenuRespVO> parents = sysMenuService.listParents();

		return parents;
	}


	//@todo 修改SysMenuResp
	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取")
	public BaseResult<SysMenuRespVO> get(@PathVariable Integer id) {
		BaseResult<SysMenuRespVO> result = new BaseResult<>();
		SysMenuRespVO sysMenu = sysMenuService.queryById(id);
		SysMenuRespVO sysMenuRespVO = new SysMenuRespVO();
		BeanUtils.copyProperties(sysMenu,sysMenuRespVO);
		return result.success(sysMenuRespVO);
	}

	@PostMapping("/save")
	@ApiOperation(value = "保存")
	public BaseResult save(@RequestBody SysMenu sysMenu){
		sysMenuService.save(sysMenu);
		return new BaseResult().success();
	}

	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public BaseResult update(@RequestBody SysMenu sysMenu) {
			sysMenuService.update(sysMenu);
		return new BaseResult().success();
	}

	@PostMapping("/delete/{id}")
	@ApiOperation(value = "删除")
	public BaseResult delete(@PathVariable Integer id) {
			sysMenuService.delete(id);
		return new BaseResult().success();
	}

}
