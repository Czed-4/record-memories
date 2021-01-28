package top.czed.record.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.czed.record.bo.MemoryBO;
import top.czed.record.commons.PageParameter;
import top.czed.record.commons.Result;
import top.czed.record.entity.Memory;
import top.czed.record.service.MemoryService;

import java.util.Map;

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
    @ApiOperation("分页查询记忆")
    public Result<PageInfo<Memory>> queryAllMemory(@RequestBody @Validated PageParameter<Memory> parameter) {
        Memory entity = parameter.getEntity();
        if (StringUtils.isBlank(entity.getUserId())) {
            return Result.fail("用户id不能为空");
        }
        Map<String, String> exParameter = parameter.getExParameter();
        PageInfo<Memory> pageInfo = memoryService.queryAllMemory(entity.getUserId(), entity.getType(), exParameter.get("keyword"), parameter.getCurrent(), parameter.getSize());
        return Result.success(pageInfo);
    }

    @PostMapping("add")
    @ApiOperation("新增记忆")
    public Result<Memory> addMemory(@RequestBody @Validated MemoryBO bo) {
        String userId = bo.getUserId();
        if (StringUtils.isBlank(userId)) {
            return Result.fail("用户id不能为空");
        }
        String url = bo.getUrl();
        if (StringUtils.isBlank(url)) {
            return Result.fail("图片地址不能为空");
        }
        String title = bo.getTitle();
        if (StringUtils.isBlank(title)) {
            return Result.fail("标题不能为空");
        }
        Memory memory = memoryService.addMemory(bo);
        return Result.success(memory, "添加记忆成功");
    }

    @GetMapping("delete")
    @ApiOperation("删除记忆")
    public Result<Integer> deleteMemory(String id) {
        if (StringUtils.isBlank(id)) {
            return Result.fail("id不可为空");
        }
        int result = memoryService.deleteMemory(id);

        return Result.success(result, "删除记忆成功");
    }

    @PostMapping("update")
    @ApiOperation("修改记忆")
    public Result<Memory> editMemory(@RequestBody @Validated MemoryBO bo) {
        if (StringUtils.isBlank(bo.getId())) {
            return Result.fail("id不能为空");
        }
        if (StringUtils.isBlank(bo.getUserId())) {
            return Result.fail("用户id不能为空");
        }
        Memory memory = memoryService.updateMemory(bo);
        return Result.success(memory, "修改记忆成功");
    }

    @GetMapping("query")
    @ApiOperation("查询记忆")
    public Result<Memory> queryMemory(@RequestParam String id) {
        if (StringUtils.isBlank(id)) {
            return Result.fail("id不能为空");
        }
        Memory memory = memoryService.queryMemory(id);
        return Result.success(memory);
    }

    @PostMapping("upload")
    @ApiOperation("上传照片")
    public Result<String> uploadPhoto(@RequestBody MultipartFile file, @RequestParam String userId) {
        System.out.println(file);
        return Result.success(null);
    }

}
