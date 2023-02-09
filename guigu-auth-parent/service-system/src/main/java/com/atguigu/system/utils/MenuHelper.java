package com.atguigu.system.utils;


import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据菜单数据构建菜单树的工具类
 * </p>
 *
 */
public class MenuHelper {

    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    // 构建树形结构
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        // 创建集合封装最终数据
        List<SysMenu> trees = new ArrayList<>();
        // 遍历所有菜单集合
        for (SysMenu sysMenu : sysMenuList) {
            // 找到递归入口，parentid=0
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    // 从根节点进行递归查询，查询子节点
    // 判断 id = parentid是否相同,如果相同是子节点，进行数据封装
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        // 数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());

        //遍历递归查找
        for (SysMenu it : treeNodes) {
            // 获取当前菜单id
            // 对比
            if(Long.parseLong(sysMenu.getId()) == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}