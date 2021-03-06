package top.czed.record.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.czed.record.bo.MemoryBO;
import top.czed.record.commons.PageParameter;
import top.czed.record.commons.Result;
import top.czed.record.entity.BsnMemory;
import top.czed.record.service.MemoryService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Value("${paths.memory.location}")
    public String memoryLocation;
    @Value("${paths.host.back}")
    private String backHost;

    @Autowired
    private MemoryService memoryService;

    @PostMapping("queryAll")
    @ApiOperation("分页查询记忆")
    public Result<PageInfo<BsnMemory>> queryAllMemory(@RequestBody @Validated PageParameter<BsnMemory> parameter) {
        BsnMemory entity = parameter.getEntity();
        String userId = entity.getUserId();
        if (StringUtils.isEmpty(userId)) {
            return Result.fail("用户ID不能为空");
        }
        Map<String, String> exParameter = parameter.getExParameter();
        PageInfo<BsnMemory> pageInfo = memoryService.queryAllMemory(entity.getUserId(), entity.getType(), exParameter.get("keyword"), parameter.getCurrent(), parameter.getSize());
        return Result.success(pageInfo);
    }

    @PostMapping("add")
    @ApiOperation("新增记忆")
    public Result<BsnMemory> addMemory(@RequestBody @Validated MemoryBO bo) {
        String userId = bo.getUserId();
        if (StringUtils.isEmpty(userId)) {
            return Result.fail("用户ID不能为空");
        }
        String url = bo.getUrl();
        if (StringUtils.isEmpty(url)) {
            return Result.fail("图片地址不能为空");
        }
        String title = bo.getTitle();
        if (StringUtils.isEmpty(title)) {
            return Result.fail("标题不能为空");
        }
        BsnMemory result = memoryService.addMemory(bo);
        return Result.success(result, "添加记忆成功");
    }

    @GetMapping("delete")
    @ApiOperation("删除记忆")
    public Result<Integer> deleteMemory(String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.fail("ID不能为空");
        }
        int result = memoryService.deleteMemory(id);

        return Result.success(result, "删除记忆成功");
    }

    @PostMapping("update")
    @ApiOperation("修改记忆")
    public Result<BsnMemory> editMemory(@RequestBody @Validated MemoryBO bo) {
        if (StringUtils.isEmpty(bo.getId())) {
            return Result.fail("ID不能为空");
        }
        if (StringUtils.isEmpty(bo.getUserId())) {
            return Result.fail("用户ID不能为空");
        }
        BsnMemory result = memoryService.updateMemory(bo);
        return Result.success(result, "修改记忆成功");
    }

    @GetMapping("query")
    @ApiOperation("查询记忆")
    public Result<BsnMemory> queryMemory(@RequestParam String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.fail("ID不能为空");
        }
        BsnMemory result = memoryService.queryMemory(id);
        return Result.success(result);
    }

    @PostMapping("upload")
    @ApiOperation("上传照片")
    public Result<String> uploadPhoto(@RequestParam String userId, @RequestBody MultipartFile file) throws IOException {
        if (StringUtils.isEmpty(userId)) {
            return Result.fail("用户ID不能为空");
        }
        if (file == null) {
            return Result.fail("图片不能为空");
        }
        // 图片存储地址
        String fileSpace = memoryLocation + File.separator + userId;
        // 文件名
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return Result.fail("文件名不能为空");
        }
        String[] split = fileName.split("\\.");
        // 获取文件尾缀
        String suffix = split[split.length - 1];
        // 自定义新名称
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 缩略图文件名
        String fileNewName = "memory-" + date + "." + suffix;
        // 原图文件名
        String fileNewNameMax = "memory-" + date + "-max." + suffix;
        // 原图最终保存路径
        String finalFileSpace = fileSpace + File.separator + fileNewNameMax;
        File outFile = new File(finalFileSpace);
        if (!outFile.exists()) {
            // 创建文件夹
            outFile.getParentFile().mkdirs();
        }
        // 存储原图
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        IOUtils.copy(file.getInputStream(), fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        // 存储缩略图
        Thumbnails.of(file.getInputStream()).size(190, 247)
                  .keepAspectRatio(false)
                  .allowOverwrite(true)
                  .toFile(new File(fileSpace + File.separator + fileNewName));

        // 返回原图路径
        String result = backHost + File.separator + userId + File.separator + fileNewName;
        return Result.success(result, "上传成功");
    }

}
