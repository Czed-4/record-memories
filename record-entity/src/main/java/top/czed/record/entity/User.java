package top.czed.record.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Czed
 * @Date 2021-1-12
 * @Description
 * @Version 1.0
 */
@Data
@ApiModel("用户信息表")
@Table(name = "u_user")
public class User {

    @Id
    @Column(name = "id")
    private String id;

    /**
     * 账号
     */
    @Column(name = "username")
    @ApiModelProperty("账号")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 是否启用 Y:启用 N:禁用
     */
    @Column(name = "status")
    @ApiModelProperty("是否启用 Y:启用 N:禁用")
    private String status;

    /**
     * 真是姓名
     */
    @Column(name = "real_name")
    @ApiModelProperty("真是姓名")
    private String realName;

    /**
     * 性别 0:女 1:男
     */
    @Column(name = "sex")
    @ApiModelProperty("性别 0:女 1:男")
    private String sex;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    @ApiModelProperty("联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email")
    @ApiModelProperty("邮箱")
    private String email;

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