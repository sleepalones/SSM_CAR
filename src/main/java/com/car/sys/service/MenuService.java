package com.car.sys.service;

import com.car.sys.domain.Menu;
import com.car.sys.utils.DataGridView;
import com.car.sys.vo.MenuVo;

import java.util.List;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 23:06
 */
public interface MenuService {

    /**
     * 菜单管理的服务接口
     * @param menuVo
     * @return
     */
    List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 根据用户id查询可用菜单
     * @param menuVo
     * @param userId
     * @return
     */
    List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

    /**
     * 查询所有菜单
     * @param menuVo
     * @return
     */
    DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 添加菜单
     * @param menuVo
     */
    void addMenu(MenuVo menuVo);

    /**
     * 修改菜单
     * @param menuVo
     */
    void updateMenu(MenuVo menuVo);

    /**
     * 根据pid查询菜单数量
     * @param pid
     * @return
     */
    Integer queryMenuByPid(Integer pid);

    /**
     * 根据id删除菜单
     * @param menuVo
     */
    void deleteMenu(MenuVo menuVo);
}
