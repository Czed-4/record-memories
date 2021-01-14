package top.czed.record.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Czed
 * @Date 2021-1-14
 * @Description
 * @Version 1.0
 */
@Data
@ApiModel("分页参数")
public class PageParameter<T> {

    @NotNull(message = "查询对象不能为空")
    @ApiModelProperty(value = "查询实体", required = true)
    private T entity;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer size = 10;

    @ApiModelProperty(value = "当前页数", example = "1")
    private Integer current = 1;

    @ApiModelProperty("扩展参数")
    private Map<String, String> exParameter = new HashMap<>(4);

}
