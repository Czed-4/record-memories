package top.czed.record.service;

import com.github.pagehelper.PageInfo;
import top.czed.record.entity.Memory;

/**
 * @Author Czed
 * @Date 2021-1-14
 * @Description
 * @Version 1.0
 */
public interface MemoryService {

    /**
     * 查询当前用户全部记忆
     *
     * @param userId  用户id
     * @param type    记忆类型
     * @param current 当前页数
     * @param size    每页数量
     * @return 全部查询结果
     */
    PageInfo<Memory> queryAllMemory(String userId, String type, Integer current, Integer size);

}
