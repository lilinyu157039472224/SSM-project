package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.AssginRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.annotation.Log;
import com.atguigu.system.enums.BusinessType;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("获取用户的角色数据")
    @GetMapping("toAssign/{userId}")
    public Result toAssign(@PathVariable String userId){
        Map<String,Object> roleMap=sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation("用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }


    /**
     * 查询所有
     *
     * @return
     */
    @ApiOperation("查询所有记录")
    @GetMapping("findAll")
//    public List<SysRole> findAllRole() {
//        //调用service
//        List<SysRole> list = sysRoleService.list();
//        return list;
//    }
    public Result findAllRole() {
        //TODO 模拟异常效果

      /*  try {
            int i=9/0;
        } catch (Exception e) {
            //手动抛出异常
           throw new GuiguException(20001,"执行了自定义异常处理");
        }*/

        //调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    /**
     * 根据id逻辑删除
     */
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
//    public boolean removeRoleById(@PathVariable Long id) {
//        //调用方法删除
//        boolean isSuccess = sysRoleService.removeById(id);
//        return isSuccess;
//    }
    public Result removeRoleId(@PathVariable Long id) {
        boolean isSuccess = sysRoleService.removeById(id);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 条件分页查询
     */
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    //page当前页 limit每页记录数
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo) {
        //创建page对象
        Page<SysRole> pageParam = new Page<>(page, limit);
        //调用service方法
        IPage<SysRole> pageModel=sysRoleService.selectPage(pageParam,sysRoleQueryVo);
        //返回
        return Result.ok(pageModel);

    }

    /**
     * 添加操作
     */
    //@RequestBody 不能使用get方式提交
    //传递json格式数据，把json格式数据封装到对象里面
    @Log(title = "角色管理",businessType = BusinessType.INSERT)
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     * 修改操作-根据id查询
     */
    @ApiOperation("根据id查询")
    @GetMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }
    /**
     * 最终修改
     */
    @ApiOperation("最终修改")
    @PutMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if (isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    /**
     * 批量删除
     * json数组格式---java的list集合
     */
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if (isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

}
