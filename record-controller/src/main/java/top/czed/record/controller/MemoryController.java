package top.czed.record.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.czed.record.commons.PageParameter;
import top.czed.record.commons.Result;
import top.czed.record.entity.Memory;
import top.czed.record.service.MemoryService;

/**
 * @Author Czed
 * @Date 2021-1-14
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("memory")
@Api(tags = "记忆控制器")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @PostMapping("queryAll")
    @ApiOperation("查询全部记忆")
    public Result<PageInfo<Memory>> queryAllMemory(@RequestBody PageParameter<Memory> parameter) {
        Memory entity = parameter.getEntity();
        String type = entity.getType();
        String userId = entity.getUserId();
        if (StringUtils.isBlank(userId)) {
            return Result.fail("用户id不可为空");
        }
        PageInfo<Memory> pageInfo = memoryService.queryAllMemory(userId, type, parameter.getCurrent(), parameter.getSize());
        long total = pageInfo.getTotal();
        if (total > 0) {
            return Result.success(pageInfo);
        }
        return Result.fail("查询结果为空");
    }

}
