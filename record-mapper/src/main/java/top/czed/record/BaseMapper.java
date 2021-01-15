package top.czed.record;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author Czed
 * @Date 2021-1-12
 * @Description
 * @Version 1.0
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
