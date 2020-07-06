package com.car.sys.service.impl;

import com.car.sys.constast.SysConstast;
import com.car.sys.dao.MenuMapper;
import com.car.sys.dao.RoleMapper;
import com.car.sys.domain.Menu;
import com.car.sys.domain.Role;
import com.car.sys.service.RoleService;
import com.car.sys.utils.DataGridView;
import com.car.sys.utils.TreeNode;
import com.car.sys.vo.RoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 铭鸽鸽
 * @create 2020/7/2 23:09
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        List<Role> data = roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addRole(RoleVo roleVo) {
        roleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public void deleteRole(Integer roleid) {
        //删除角色表的数据
        roleMapper.deleteByPrimaryKey(roleid);
        //根据角色id删除sys_role_role里面的数据
        roleMapper.deleteRoleMenuByRid(roleid);
        //根据角色id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByRid(roleid);
    }

    @Override
    public void deleteBatchBole(Integer[] ids) {
        for (Integer id : ids) {
            deleteRole(id);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        //查询所有的可用菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        //根据角色id查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE,roleid);

        List<TreeNode> data = new ArrayList<>();
        for (Menu m1 : allMenu) {
            String checkArr = SysConstast.CODE_ZERO;
            for (Menu m2 : roleMenu) {
                if (m1.getId().equals(m2.getId())) {
                    checkArr = SysConstast.CODE_ONE;
                    break;
                }
            }
            data.add(new TreeNode(m1.getId(),m1.getPid(),m1.getTitle(),m1.getSpread().equals(SysConstast.SPREAD_TRUE),checkArr));
        }
        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid = roleVo.getRoleid();
        Integer[] mids = roleVo.getIds();
        //根据rid删除sys_role_menu里面所有的数据
        roleMapper.deleteRoleMenuByRid(rid);
        for (Integer mid : mids) {
            roleMapper.insertRoleMenu(rid,mid);
        }
    }
}
