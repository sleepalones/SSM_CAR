package com.car.sys.service;

import com.car.sys.domain.User;
import com.car.sys.utils.DataGridView;
import com.car.sys.vo.UserVo;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 14:24
 */
public interface UserService {
    /**
     * 用户登录
     * @param userVo
     * @return
     */
    User login(UserVo userVo);

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    DataGridView queryAllUser(UserVo userVo);

    /**
     * 添加用户
     * @param userVo
     */
    void addUser(UserVo userVo);

    /**
     * 修改用户
     * @param userVo
     */
    void updateUser(UserVo userVo);

    /**
     * 根据id删除用户
     * @param userid
     */
    void deleteUser(Integer userid);
    /**
     * 批量删除用户
     * @param ids
     */
    void deleteBatchUser(Integer [] ids);

    /**
     * 重置密码
     * @param userid
     */
    void resetUserPwd(Integer userid);

    /**
     * 加载用户管理的分配角色的数据
     * @param userid
     * @return
     */
    DataGridView queryUserRole(Integer userid);

    /**
     * 保存用户和角色的关系
     * @param userVo
     */
    void saveUserRole(UserVo userVo);
}
