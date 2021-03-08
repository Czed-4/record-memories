package top.czed.record.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.czed.record.commons.utils.EncryptionUtil;
import top.czed.record.entity.SysUser;
import top.czed.record.mapper.UserMapper;
import top.czed.record.service.UserService;

import java.util.Date;

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
    public SysUser login(String username, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        String encryption = EncryptionUtil.encryption(password);
        sysUser.setPassword(encryption);
        return userMapper.selectOne(sysUser);
    }

    @Override
    public SysUser update(SysUser user) {
        // 判断是否需要修改密码
        String password = user.getPassword();
        if (!StringUtils.isEmpty(password)) {
            String encryption = EncryptionUtil.encryption(password);
            user.setPassword(encryption);
        }
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public SysUser getUserByName(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        return userMapper.selectOne(sysUser);
    }

}
