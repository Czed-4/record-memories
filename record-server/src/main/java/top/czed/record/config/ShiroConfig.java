package top.czed.record.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.czed.record.commons.utils.EncryptionUtil;

/**
 * @Author Czed
 * @Date 2021-2-2
 * @Description
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm getRealm() {
        Realm realm = new Realm();
        realm.setCredentialsMatcher(getCredentialsMatcher());
        return realm;
    }

    @Bean
    public DefaultWebSecurityManager getManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(getRealm());
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean getFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(getManager());
        return factoryBean;
    }

    @Bean
    public HashedCredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(EncryptionUtil.HASH_ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(EncryptionUtil.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

}
