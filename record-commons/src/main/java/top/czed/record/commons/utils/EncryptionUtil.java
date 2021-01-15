package top.czed.record.commons.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Author Czed
 * @Date 2021-1-15
 * @Description
 * @Version 1.0
 */
public class EncryptionUtil {

    /**
     * 哈希散列算法的名字
     */
    public final static String HASH_ALGORITHM_NAME = "MD5";

    /**
     * 哈希加密盐值
     */
    public final static String HASH_SALT = "WelcomeToYourMemoriesPalace";

    /**
     * 哈希加密迭代次數
     */
    public final static int HASH_ITERATIONS = 14;

    /**
     * 对明文密码进行加盐加密
     *
     * @param password 需要加密的密码
     * @return 加密后的密码
     */
    public static String encryption(String password) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, HASH_SALT, HASH_ITERATIONS).toString();
    }

}
