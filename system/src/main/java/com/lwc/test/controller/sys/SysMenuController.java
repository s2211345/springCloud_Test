package com.lwc.test.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lwc.test.service.sys.SysMenuService;
import com.lwc.test.view.sys.response.SysResult;
import com.lwc.test.model.sys.SysMenu;
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
@Api("菜单表")
@RestController
@RequestMapping("/admin/sysmenu")
@Slf4j
public class SysMenuController{

	@Autowired
	private SysMenuService sysMenuService;

	@PostMapping("/getList")
	@ApiOperation(value = "列表")
	public SysResult<List<SysMenuRespVO>> list(@RequestBody SysMenuReqVO sysMenuReqVO) {
		if(0 != sysMenuReqVO.getPage() && 0 != sysMenuReqVO.getLimit()){
			int page = (sysMenuReqVO.getPage()-1) * sysMenuReqVO.getLimit();
				sysMenuReqVO.setPage(page);
		}
		SysResult<List<SysMenuRespVO>> result = new SysResult<>();
		List<SysMenuRespVO> sysMenuRespVOS = sysMenuService.listByReq(sysMenuReqVO);
		int count = sysMenuService.countByReq(sysMenuReqVO);
		result.setData(sysMenuRespVOS);
		result.setCount(count);
		return result;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取")
	public SysMenu get(@PathVariable Integer id) {
		return sysMenuService.queryById(id);
	}

	@PostMapping("/save")
	@ApiOperation(value = "保存")
	public SysMenu save(@RequestBody SysMenu sysMenu){
		sysMenuService.save(sysMenu);
		return sysMenu;
	}

	@PutMapping("/update")
	@ApiOperation(value = "修改")
	public SysMenu update(@RequestBody SysMenu sysMenu) {
			sysMenuService.update(sysMenu);
		return sysMenu;
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "删除")
	public void delete(@PathVariable Integer id) {
			sysMenuService.delete(id);
	}

}
