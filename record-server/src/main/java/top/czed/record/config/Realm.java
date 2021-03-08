package top.czed.record.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import top.czed.record.commons.utils.EncryptionUtil;
import top.czed.record.entity.SysUser;
import top.czed.record.service.UserService;

/**
 * @Author Czed
 * @Date 2021-2-2
 * @Description
 * @Version 1.0
 */
@Configuration
public class Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser sysUser = userService.getUserByName(token.getUsername());
        if (sysUser == null) {
            return null;
        }
        ByteSource bytes = ByteSource.Util.bytes(EncryptionUtil.HASH_SALT);
        return new SimpleAuthenticationInfo(sysUser.getUsername(), sysUser.getPassword(), bytes, getName());
    }

}
