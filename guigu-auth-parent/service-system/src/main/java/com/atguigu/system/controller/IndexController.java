package com.atguigu.system.controller;


import com.atguigu.common.result.Result;
import com.atguigu.common.utils.JwtHelper;
import com.atguigu.common.utils.MD5;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    //{"code":20000,"data":{"token":"admin-token"}}

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录功能
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        //根据username查询数据
        SysUser sysUser=sysUserService.getUserInfoByUserName(loginVo.getUsername());
        //如果查询为空
        if (sysUser == null){
            throw new GuiguException(20001,"用户不存在");
        }
        //判断密码是否一致
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if (!sysUser.getPassword().equals(md5Password)){
            throw new GuiguException(20001,"密码不正确");
        }
        //判断用户是否可用
        if (sysUser.getStatus().intValue()==0){
            throw new GuiguException(20001,"用户已经被禁用");
        }
        //根据userid和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        //获取请求头token字符串
        String token = request.getHeader("token");
        //从token字符串获取用户名称(id)
        String username = JwtHelper.getUsername(token);
        // 根据用户名称获取用户信息(基本信息 和 菜单权限 和按钮权限数据)
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    /**
     * 退出操作
     */
    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }


}
