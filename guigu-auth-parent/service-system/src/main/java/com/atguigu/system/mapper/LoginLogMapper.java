package com.atguigu.system.mapper;
import com.atguigu.model.system.SysLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lilinyu
 * @since 2023-02-09
 */
@Repository
public interface LoginLogMapper extends BaseMapper<SysLoginLog> {

}
