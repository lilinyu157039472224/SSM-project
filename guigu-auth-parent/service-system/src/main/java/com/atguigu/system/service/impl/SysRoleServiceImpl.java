package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.model.vo.AssginRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.mapper.SysUserRoleMapper;
import com.atguigu.system.service.SysRoleService;
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


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    /** @Override
     * 条件分页查询
     * @param pageParam
     * @param sysRoleQueryVo
     * @return
     */
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    /**
     * 获取用户的角色数据
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> getRolesByUserId(String userId) {
        // 查询所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        // 根据用户id查询已经分配角色
        QueryWrapper<SysUserRole> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);
        // 从userRolesList集合获取所有角色id
        List<String> userRoleIds=new ArrayList<>();
        for (SysUserRole userRole:userRolesList) {
            String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        // 封装到Map集合
        Map<String,Object> returnMap=new HashMap<>();
        returnMap.put("allRoles",roles);
        returnMap.put("userRoleIds",userRoleIds);
        return returnMap;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        // 根据用户id删除之前分配角色
        QueryWrapper<SysUserRole> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        // 获取所有角色id，添加角色用户关系表
        // 角色id列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String roleId:roleIdList) {
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }
}
