package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.RouterVo;
import com.atguigu.model.vo.SysUserQueryVo;
import com.atguigu.system.mapper.SysUserMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lilinyu
 * @since 2023-02-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 用户列表
     *
     * @param pageParam
     * @param sysUserQueryVo
     * @return
     */
    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        IPage<SysUser> pageModel = baseMapper.selectPage(pageParam, sysUserQueryVo);
        return pageModel;
    }

    /**
     * 更改用户状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(String id, Integer status) {
        //根据id查询用户
        SysUser sysUser = baseMapper.selectById(id);
        //设置修改状态
        sysUser.setStatus(status);
        //调用方法修改
        baseMapper.updateById(sysUser);


    }

    //username查询
    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return baseMapper.selectOne(wrapper);
    }

    //根据用户名称获取用户信息(基本信息 和 菜单权限 和按钮权限数据)
    @Override
    public Map<String, Object> getUserInfo(String username) {
        //根据username查询用户基本信息
        SysUser sysUser = this.getUserInfoByUserName(username);
        //根据userid查询菜单权限值
        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(sysUser.getId());
        //根据userid查询按钮权限制
        List<String> permsList = sysMenuService.getUserButtonList(sysUser.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("name", username);
        result.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        result.put("roles", sysUser.getName());
        //菜单权限数据
        result.put("routers", routerVoList);
        //按钮权限数据
        result.put("buttons", permsList);

        return result;
    }
}
