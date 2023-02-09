package com.atguigu.system.test;

import com.atguigu.model.system.SysRole;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询所有操作
     */
    @Test
    public void findAll() {
        List<SysRole> list = sysRoleService.list();
        for (SysRole sysRole : list) {
            System.out.println(sysRole);
        }
    }

    /**
     * 添加操作
     */
    @Test
    public void save(){
        SysRole sysRole=new SysRole();
        sysRole.setRoleName("角色管理员1");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员1");
        boolean result = sysRoleService.save(sysRole);
        System.out.println(result);
    }

    /**
     * 修改操作
     */
    @Test
    public void update(){
        SysRole sysRole = sysRoleService.getById(8);
        sysRole.setDescription("test");
        boolean result = sysRoleService.updateById(sysRole);
        System.out.println(result);
    }
    /**
     * 删除操作
     */
    @Test
    public void remove(){
        boolean result = sysRoleService.removeById(1596490445259616257L);
        System.out.println(result);
    }
    /**
     * 条件查询
     */
      @Test
    public void getCondition(){
          QueryWrapper<SysRole> wrapper=new QueryWrapper<>();
          wrapper.like("role_name","用户");
          List<SysRole> list = sysRoleService.list(wrapper);
          System.out.println(list);
      }
}
