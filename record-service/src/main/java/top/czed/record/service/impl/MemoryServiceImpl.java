package top.czed.record.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import top.czed.record.bo.MemoryBO;
import top.czed.record.commons.utils.GenerateIdUtil;
import top.czed.record.entity.BsnMemory;
import top.czed.record.mapper.MemoryMapper;
import top.czed.record.service.MemoryService;

import java.util.Date;
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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public PageInfo<BsnMemory> queryAllMemory(String userId, String type, String keyword, Integer current, Integer size) {
        Example example = new Example(BsnMemory.class);
        example.orderBy("createTime").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId)
                .andEqualTo("isDelete", "N");
        if (!StringUtils.isEmpty(type)) {
            criteria.andEqualTo("type", type);
        }
        if (!StringUtils.isEmpty(keyword)) {
            example.and(example.createCriteria()
                    .orLike("title", "%" + keyword + "%")
                    .orLike("place", "%" + keyword + "%")
                    .orLike("detail", "%" + keyword + "%"));
        }
        PageHelper.startPage(current, size);
        List<BsnMemory> memories = memoryMapper.selectByExample(example);
        return new PageInfo<>(memories);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BsnMemory addMemory(MemoryBO memoryBO) {
        BsnMemory entity = new BsnMemory();
        BeanUtils.copyProperties(memoryBO, entity);
        String id = GenerateIdUtil.get();
        entity.setId(id);
        entity.setIsDelete("N");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        memoryMapper.insert(entity);
        return memoryMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int deleteMemory(String id) {
        BsnMemory entity = new BsnMemory();
        entity.setUpdateTime(new Date());
        entity.setIsDelete("Y");
        Example example = new Example(BsnMemory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return memoryMapper.updateByExampleSelective(entity,example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BsnMemory updateMemory(MemoryBO memoryBO) {
        BsnMemory entity = new BsnMemory();
        BeanUtils.copyProperties(memoryBO, entity);
        entity.setUpdateTime(new Date());
        Example example = new Example(BsnMemory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", entity.getId())
                .andEqualTo("userId", entity.getUserId());
        memoryMapper.updateByExampleSelective(entity, example);
        return memoryMapper.selectByPrimaryKey(entity.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public BsnMemory queryMemory(String id) {
        return memoryMapper.selectByPrimaryKey(id);
    }

}
