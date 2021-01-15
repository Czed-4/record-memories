package top.czed.record.commons.utils;

import java.util.UUID;

/**
 * @Author Czed
 * @Date 2021-1-15
 * @Description
 * @Version 1.0
 */
public class GenerateIdUtil {

    public static String get() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
