package top.czed.record.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Czed
 * @Date 2021-1-14
 * @Description
 * @Version 1.0
 */
@Data
@ApiModel("记忆详情请求实体")
public class MemoryBO {

    /**
     * 记忆id
     */
    @ApiModelProperty("记忆id")
    private String id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private String userId;

    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String url;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 记忆类型 1:个人成长 2:情感记忆
     */
    @ApiModelProperty("记忆类型 1:个人成长 2:情感记忆")
    private String type;

    /**
     * 地点
     */
    @ApiModelProperty("地点")
    private String place;

    /**
     * 详情说明
     */
    @ApiModelProperty("详情说明")
    private String detail;

    /**
     * 是否逻辑删除 Y:是 N:否
     */
    @ApiModelProperty("是否逻辑删除 Y:是 N:否")
    private String isDelete;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}