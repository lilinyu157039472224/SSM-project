package com.atguigu.system.test;

import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询所有操作
     */
    @Test
    public void findAll() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }
    }

    /**
     * 新增操作
     */
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("keke");
        sysRole.setRoleCode("testManager");
        sysRole.setDescription("测试角色");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(rows > 0 ? "添加成功" : "添加失败");
    }
    /**
     * 修改操作
     */
    @Test
    public void update(){
        //根据id查询
        SysRole sysRole = sysRoleMapper.selectById(8);
        //设置新值
        sysRole.setDescription("用户管理员");
        //执行修改操作
        int rows = sysRoleMapper.updateById(sysRole);
        System.out.println(rows>0?"修改成功":"修改失败");
    }
    /**
     * 删除操作
     */
    @Test
    public void deleteById(){
        int rows = sysRoleMapper.deleteById(1596489540107771905L);
        System.out.println(rows>0?"删除成功":"删除失败");
    }
    /**
     * 批量删除
     */
    @Test
    public void deleteByIds(){
        int rows = sysRoleMapper.deleteBatchIds(Arrays.asList(1596490445259616257L, 1596490387470536706L));
        System.out.println(rows>0?"批量删除成功":"批量删除失败");
    }

    /**
     * 条件查询
     */
    @Test
    public void select(){
        //创建条件构造器对象
        QueryWrapper<SysRole> queryWrapper=new QueryWrapper<>();
        //设置条件
        queryWrapper.like("role_name","管理员");
        //调用方法
        List<SysRole> list = sysRoleMapper.selectList(queryWrapper);
        System.out.println(list);
    }
    /**
     * 条件删除
     */
   @Test
    public void delete(){
       QueryWrapper<SysRole> queryWrapper=new QueryWrapper<>();
       queryWrapper.like("role_name","keke1");
       sysRoleMapper.delete(queryWrapper);
   }

}
