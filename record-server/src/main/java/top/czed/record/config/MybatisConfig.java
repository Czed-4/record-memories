package top.czed.record.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author Czed
 * @Date 2021-1-11
 * @Description
 * @Version 1.0
 */
@Configuration
@MapperScan("top.czed.record.mapper")
public class MybatisConfig {
}
