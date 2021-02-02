package top.czed.record.service;

import top.czed.record.entity.User;

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
    User login(String username, String password);

    /**
     * 通过账号获取用户信息
     *
     * @param username 账号
     * @return 用户信息
     */
    User getUserByName(String username);

}
