package com.atguigu.system.service;

import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lilinyu
 * @since 2023-02-03
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户列表
     * @param pageParam
     * @param sysUserQueryVo
     * @return
     */
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    /**
     * 更改用户状态
     * @param id
     * @param status
     */
    void updateStatus(String id, Integer status);

    /**
     * 根据用户名称查询
     * @param username
     * @return
     */
    SysUser getUserInfoByUserName(String username);

    Map<String, Object> getUserInfo(String username);
}
