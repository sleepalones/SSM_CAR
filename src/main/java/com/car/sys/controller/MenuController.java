package com.car.sys.controller;

import com.car.sys.constast.SysConstast;
import com.car.sys.domain.Menu;
import com.car.sys.domain.User;
import com.car.sys.service.MenuService;
import com.car.sys.utils.*;
import com.car.sys.vo.MenuVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 23:10
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/loadIndexLeftMenuJson.do")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
        //得到当前登录的用户对象
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<Menu> list = null;
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        if (user.getType().equals(SysConstast.USER_TYPE_SUPER)) {
            list = menuService.queryAllMenuForList(menuVo);
        }else {
            list = menuService.queryMenuByUserIdForList(menuVo,user.getUserid());
        }
        List<TreeNode> nodes = new ArrayList<>();
        for (Menu menu : list) {
            nodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),menu.getIcon(),
                    menu.getHref(), menu.getSpread().equals(SysConstast.SPREAD_TRUE),menu.getTarget()));
        }
        return TreeNodeBuilder.builder(nodes,1);
    }

    /**
     * 加载菜单管理左边的菜单树
     * @param menuVo
     * @return
     */
    @RequestMapping(value = "/loadMenuManagerLeftTreeJson.do",method = RequestMethod.POST)
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo){
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> list = menuService.queryAllMenuForList(menuVo);
        List<TreeNode> nodes = new ArrayList<>();
        for (Menu menu : list) {
            nodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),menu.getIcon(),
                    menu.getHref(),menu.getSpread().equals(SysConstast.SPREAD_TRUE),menu.getTarget()));
        }
        return new DataGridView(nodes);
    }

    /**
     * 加载菜单列表返回DataGridView
     */
    @RequestMapping("/loadAllMenu.do")
    public DataGridView loadAllMenu(MenuVo menuVo){
        return menuService.queryAllMenu(menuVo);
    }

    /**
     * 添加菜单
     */
    @RequestMapping("/addMenu.do")
    public ResultObj addMenu(MenuVo menuVo){
        try {
            menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     */
    @RequestMapping("/updateMenu.do")
    public ResultObj updateMenu(MenuVo menuVo){
        try {
            menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * 有返回code>0
     * 没有返回code<0
     */
    @RequestMapping("/checkMenuHasChildren.do")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        //根据pid查询菜单数量
        Integer count = menuService.queryMenuByPid(menuVo.getId());
        if (count > 0){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 删除菜单
     */
    @RequestMapping("/deleteMenu.do")
    public ResultObj deleteMenu(MenuVo menuVo){
        try {
            menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
