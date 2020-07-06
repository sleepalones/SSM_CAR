package com.car.sys.service;

import com.car.sys.utils.DataGridView;
import com.car.sys.vo.RoleVo;

/**
 * @author 铭鸽鸽
 * @create 2020/7/2 23:05
 */
public interface RoleService {

    /**
     * 查询所有用户
     * @param roleVo
     * @return
     */
    DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 添加角色
     * @param roleVo
     */
    void addRole(RoleVo roleVo);

    /**
     * 修改角色
     * @param roleVo
     */
    void updateRole(RoleVo roleVo);

    /**
     * 根据id删除角色
     * @param roleid
     */
    void deleteRole(Integer roleid);

    /**
     * 批量删除角色
     * @param ids
     */
    void deleteBatchBole(Integer[] ids);

    /**
     * 加载角色管理分配菜单的json
     * @param roleid
     * @return
     */
    DataGridView initRoleMenuTreeJson(Integer roleid);

    /**
     * 保存角色和菜单的关系
     * @param roleVo
     */
    void saveRoleMenu(RoleVo roleVo);
}
