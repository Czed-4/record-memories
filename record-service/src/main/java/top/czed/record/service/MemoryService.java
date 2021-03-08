package top.czed.record.service;

import com.github.pagehelper.PageInfo;
import top.czed.record.bo.MemoryBO;
import top.czed.record.entity.BsnMemory;

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
    PageInfo<BsnMemory> queryAllMemory(String userId, String type, String keyword, Integer current, Integer size);

    /**
     * 新增当前用户记忆
     *
     * @param memoryBO 记忆参数
     * @return 新增结果
     */
    BsnMemory addMemory(MemoryBO memoryBO);

    /**
     * 删除记忆
     *
     * @param id 记忆id
     * @return 删除条数
     */
    int deleteMemory(String id);

    /**
     * 修改当前用户记忆
     *
     * @param memoryBO 记忆参数
     * @return 修改结果
     */
    BsnMemory updateMemory(MemoryBO memoryBO);

    /**
     * 查询当前用户记忆
     *
     * @param id 记忆id
     * @return 查询结果
     */
    BsnMemory queryMemory(String id);

}
