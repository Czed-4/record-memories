package top.czed.record.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.czed.record.bo.MemoryBO;
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
@Validated
@Api(tags = "记忆控制器")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @PostMapping("queryAll")
    @ApiOperation("查询全部记忆")
    public Result<PageInfo<Memory>> queryAllMemory(@RequestBody @Validated PageParameter<Memory> parameter) {
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

    @PostMapping("add")
    @ApiOperation("新增记忆")
    public Result<Memory> addMemory(@RequestBody @Validated MemoryBO bo) {
        String userId = bo.getUserId();
        if (StringUtils.isBlank(userId)) {
            return Result.fail("用户id不可为空");
        }
        String url = bo.getUrl();
        if (StringUtils.isBlank(url)) {
            return Result.fail("图片不能为空");
        }
        String title = bo.getTitle();
        if (StringUtils.isBlank(title)) {
            return Result.fail("标题不能为空");
        }
        Memory memory = memoryService.addMemory(bo);
        if (memory == null) {
            return Result.fail("添加记忆失败");
        }
        return Result.success(memory, "添加记忆成功");
    }

    @PostMapping({"delete", "update"})
    @ApiOperation("删除、修改记忆")
    public Result<Memory> deleteOrUpdateMemory(@RequestBody @Validated MemoryBO bo) {
        String userId = bo.getUserId();
        if (StringUtils.isBlank(userId)) {
            return Result.fail("用户id不可为空");
        }
        Memory memory = memoryService.deleteOrUpdateMemory(bo);
        String msg;
        if (memory == null) {
            msg = StringUtils.isBlank(bo.getIsDelete()) ? "修改记忆失败" : "删除记忆失败";
            return Result.fail(msg);
        }
        msg = StringUtils.isBlank(bo.getIsDelete()) ? "修改记忆成功" : "删除记忆成功";
        return Result.success(memory, msg);
    }

    @GetMapping("query")
    @ApiOperation("查询记忆")
    public Result<Memory> queryMemory(@RequestParam String id) {
        if (StringUtils.isBlank(id)) {
            return Result.fail("记忆id不可为空");
        }
        Memory memory = memoryService.queryMemory(id);
        if (memory == null) {
            return Result.fail("查询结果为空");
        }
        return Result.success(memory);
    }

}
