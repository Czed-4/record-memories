package top.czed.record.service;

import top.czed.record.entity.SysUser;

/**
 * @Author Czed
 * @Date 2021-1-12
 * @Description
 * @Version 1.0
 */
public interface UserService {

    /**
     * 登录
     *
     * @param username 账号
     * @param password 密码
     * @return 登录用户信息
     */
    SysUser login(String username, String password);

    /**
     * 修改用户信息
     *
     * @param user 修改参数
     * @return 修改后的用户信息
     */
    SysUser update(SysUser user);

    /**
     * 通过账号获取用户信息
     *
     * @param username 账号
     * @return 用户信息
     */
    SysUser getUserByName(String username);

}
