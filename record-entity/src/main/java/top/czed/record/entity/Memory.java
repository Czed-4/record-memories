package top.czed.record.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @Author Czed
 * @Date 2021-1-14
 * @Description
 * @Version 1.0
 */
@Data
@ApiModel("记忆详情表")
@Table(name = "m_memory")
public class Memory {

    @Id
    @Column(name = "id")
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private String userId;

    /**
     * 图片地址
     */
    @Column(name = "url")
    @ApiModelProperty("图片地址")
    private String url;

    /**
     * 标题
     */
    @Column(name = "title")
    @ApiModelProperty("标题")
    private String title;

    /**
     * 记忆类型 1:个人成长 2:情感记忆
     */
    @Column(name = "type")
    @ApiModelProperty("记忆类型 1:个人成长 2:情感记忆")
    private String type;

    /**
     * 详情说明
     */
    @Column(name = "detail")
    @ApiModelProperty("详情说明")
    private String detail;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;
}