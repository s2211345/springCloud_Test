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

import com.lwc.test.service.sys.SysLogService;
import com.lwc.test.view.sys.response.SysResult;
import com.lwc.test.model.sys.SysLog;
import com.lwc.test.view.sys.request.SysLogReqVO;
import com.lwc.test.view.sys.response.SysLogRespVO;

import java.util.List;

/**
 * 用户操作记录
 *
 * @author lwc
 * @email lwc@xwckeji.com
 * @date 2020-07-15 11:25:46
 */
@Api("用户操作记录")
@RestController
@RequestMapping("/admin/syslog")
@Slf4j
public class SysLogController{

	@Autowired
	private SysLogService sysLogService;

	@PostMapping("/getList")
	@ApiOperation(value = "列表")
	public SysResult<List<SysLogRespVO>> list(@RequestBody SysLogReqVO sysLogReqVO) {
		if(0 != sysLogReqVO.getPage() && 0 != sysLogReqVO.getLimit()){
			int page = (sysLogReqVO.getPage()-1) * sysLogReqVO.getLimit();
				sysLogReqVO.setPage(page);
		}
		SysResult<List<SysLogRespVO>> result = new SysResult<>();
		List<SysLogRespVO> sysLogRespVOS = sysLogService.listByReq(sysLogReqVO);
		int count = sysLogService.countByReq(sysLogReqVO);
		result.setData(sysLogRespVOS);
		result.setCount(count);
		return result;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取")
	public BaseResult<SysLogRespVO> get(@PathVariable Integer id) {
		BaseResult<SysLogRespVO> result = new BaseResult<>();
		SysLogRespVO logRespVO = sysLogService.queryById(id);
		SysLogRespVO resultData = new SysLogRespVO();
		BeanUtils.copyProperties(logRespVO,resultData);
		return result.success(resultData);
	}

	@PostMapping("/save")
	@ApiOperation(value = "保存")
	public BaseResult save(@RequestBody SysLog sysLog){
		sysLogService.save(sysLog);
		return new BaseResult().success();
	}

	@PutMapping("/update")
	@ApiOperation(value = "修改")
	public BaseResult update(@RequestBody SysLog sysLog) {
		sysLogService.update(sysLog);
		return new BaseResult().success();
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "删除")
	public BaseResult delete(@PathVariable Integer id) {
		sysLogService.delete(id);
		return new BaseResult().success();
	}

}
