package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysLoginLog;
import com.atguigu.model.vo.SysLoginLogQueryVo;
import com.atguigu.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysLoginLogController {
	
	@Resource
	private LoginLogService loginLogService;

	@ApiOperation(value = "获取分页列表")
	@GetMapping("{page}/{limit}")
	public Result index(@PathVariable Long page,
		               @PathVariable Long limit,
                       SysLoginLogQueryVo sysLoginLogQueryVo) {
		Page<SysLoginLog> pageParam = new Page<>(page, limit);
		IPage<SysLoginLog> pageModel = loginLogService.selectPage(page,limit, sysLoginLogQueryVo);
		return Result.ok(pageModel);
	}

}