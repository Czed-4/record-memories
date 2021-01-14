package top.czed.record.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import top.czed.record.entity.Memory;
import top.czed.record.mapper.MemoryMapper;
import top.czed.record.service.MemoryService;

import java.util.List;

/**
 * @Author Czed
 * @Date 2021-1-14
 * @Description
 * @Version 1.0
 */
@Service
public class MemoryServiceImpl implements MemoryService {

    @Autowired
    private MemoryMapper memoryMapper;

    @Override
    public PageInfo<Memory> queryAllMemory(String userId, String type, Integer current, Integer size) {
        Example example = new Example(Memory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        if (StringUtils.isNotBlank(type)) {
            criteria.andEqualTo("type", type);
        }
        PageHelper.startPage(current, size);
        List<Memory> memories = memoryMapper.selectByExample(example);
        return new PageInfo<>(memories);
    }

}
