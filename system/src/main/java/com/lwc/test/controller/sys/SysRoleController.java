package com.lwc.test.controller.sys;

import com.lwc.test.view.base.response.BaseResult;
import com.lwc.test.view.sys.response.SysMenuRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lwc.test.service.sys.SysRoleService;
import com.lwc.test.view.sys.response.SysResult;
import com.lwc.test.model.sys.SysRole;
import com.lwc.test.view.sys.request.SysRoleReqVO;
import com.lwc.test.view.sys.response.SysRoleRespVO;

import java.util.List;

/**
 * 角色表
 *
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:47:00
 */
@Api("角色表")
@RestController
@RequestMapping("/admin/sysrole")
@Slf4j
public class SysRoleController{

	@Autowired
	private SysRoleService sysRoleService;

	@PostMapping("/getList")
	@ApiOperation(value = "列表")
	public SysResult<List<SysRoleRespVO>> list(@RequestBody SysRoleReqVO sysRoleReqVO) {
		if(0 != sysRoleReqVO.getPage() && 0 != sysRoleReqVO.getLimit()){
			int page = (sysRoleReqVO.getPage()-1) * sysRoleReqVO.getLimit();
				sysRoleReqVO.setPage(page);
		}
		SysResult<List<SysRoleRespVO>> result = new SysResult<>();
		List<SysRoleRespVO> sysRoleRespVOS = sysRoleService.listByReq(sysRoleReqVO);
		int count = sysRoleService.countByReq(sysRoleReqVO);
		result.setData(sysRoleRespVOS);
		result.setCount(count);
		return result;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取")
	public BaseResult<SysRoleRespVO> get(@PathVariable Integer id) {
		BaseResult<SysRoleRespVO> result = new BaseResult<>();
		SysRoleRespVO roleRespVO = sysRoleService.queryById(id);
		SysRoleRespVO resultData = new SysRoleRespVO();
		BeanUtils.copyProperties(roleRespVO,resultData);
		return result.success(resultData);
	}

	@PostMapping("/save")
	@ApiOperation(value = "保存")
	public BaseResult save(@RequestBody SysRole sysRole){
		sysRoleService.save(sysRole);
		return new BaseResult().success();
	}

	@PutMapping("/update")
	@ApiOperation(value = "修改")
	public BaseResult update(@RequestBody SysRole sysRole) {
			sysRoleService.update(sysRole);
		return new BaseResult().success();
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "删除")
	public BaseResult delete(@PathVariable Integer id) {
			sysRoleService.delete(id);
			return new BaseResult().success();
	}

}
