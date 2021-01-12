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
     * 登入认证
     *
     * @param username 账号
     * @param password 密码
     * @return 登入用户信息
     */
    User login(String username, String password);

}
