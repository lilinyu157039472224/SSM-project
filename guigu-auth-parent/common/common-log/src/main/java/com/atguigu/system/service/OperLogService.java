package com.atguigu.system.service;

import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface OperLogService {
    public void saveSysLog(SysOperLog sysOperLog);

    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
