package com.atguigu.system.mapper;

import com.atguigu.model.system.SysOperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OperLogMapper extends BaseMapper<SysOperLog> {
}
