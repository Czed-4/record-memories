package top.czed.record.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.czed.record.commons.utils.EncryptionUtil;
import top.czed.record.entity.User;
import top.czed.record.mapper.UserMapper;
import top.czed.record.service.UserService;

/**
 * @Author Czed
 * @Date 2021-1-12
 * @Description
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String encryption = EncryptionUtil.encryption(password);
        user.setPassword(encryption);
        return userMapper.selectOne(user);
    }

}
