package top.czed.record.service;

import com.github.pagehelper.PageInfo;
import top.czed.record.bo.MemoryBO;
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
     * @param keyword 关键词
     * @param current 当前页数
     * @param size    每页数量
     * @return 全部查询结果
     */
    PageInfo<Memory> queryAllMemory(String userId, String type, String keyword, Integer current, Integer size);

    /**
     * 新增当前用户记忆
     *
     * @param memoryBO 记忆参数
     * @return 新增结果
     */
    Memory addMemory(MemoryBO memoryBO);

    /**
     * 删除、修改当前用户记忆
     *
     * @param memoryBO 记忆参数
     * @return 删除、修改结果
     */
    Memory editMemory(MemoryBO memoryBO);

    /**
     * 查询当前用户记忆
     *
     * @param id 记忆id
     * @return 查询结果
     */
    Memory queryMemory(String id);


}
