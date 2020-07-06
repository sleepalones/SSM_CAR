package com.car.sys.service.impl;

import com.car.sys.constast.SysConstast;
import com.car.sys.dao.RoleMapper;
import com.car.sys.dao.UserMapper;
import com.car.sys.domain.Role;
import com.car.sys.domain.User;
import com.car.sys.service.UserService;
import com.car.sys.utils.DataGridView;
import com.car.sys.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 14:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public User login(UserVo userVo) {
        //将密码加密
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }

    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<User> data = userMapper.queryAllUser(userVo);
        return new DataGridView(page.getTotal(), data);

    }

    @Override
    public void addUser(UserVo userVo) {
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        // 设置用户类型 都是普通用户type=2
        userVo.setType(SysConstast.USER_TYPE_NORMAL);
        userMapper.insertSelective(userVo);
    }

    @Override
    public void updateUser(UserVo userVo) {
        userMapper.updateByPrimaryKeySelective(userVo);
    }

    @Override
    public void deleteUser(Integer userid) {
        // 删除用户
        userMapper.deleteByPrimaryKey(userid);
        // 根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(userid);

    }

    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer uid : ids) {
            this.deleteUser(uid);
        }
    }

    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setUserid(userid);
        // 设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //更新
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public DataGridView queryUserRole(Integer userid) {
        //查询所有可用角色
        Role role = new Role();
        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Role> allRole = roleMapper.queryAllRole(role);
        //根据用户ID查询已拥有的角色
        List<Role> userRole = roleMapper.queryRoleByUid(SysConstast.AVAILABLE_TRUE,userid);
        List<Map<String ,Object>> data = new ArrayList<>();
        for (Role r1 : allRole) {
            boolean layChecked = false;
            for (Role r2 : userRole) {
                if(r1.getRoleid().equals(r2.getRoleid())) {
                    layChecked = true;
                }
            }
            Map<String ,Object> map = new HashMap<>(5);
            map.put("roleid",r1.getRoleid());
            map.put("rolename",r1.getRolename());
            map.put("roledesc",r1.getRoledesc());
            map.put("LAY_CHECKED",layChecked);
            data.add(map);
        }
        return new DataGridView(data);
    }

    @Override
    public void saveUserRole(UserVo userVo) {
        Integer userid = userVo.getUserid();
        Integer[] roleIds = userVo.getIds();
        //根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(userid);
        //保存关系
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                userMapper.insertUserRole(userid,roleId);
            }
        }
    }
}
