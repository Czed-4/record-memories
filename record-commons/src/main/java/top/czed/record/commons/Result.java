package top.czed.record.commons;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Czed
 * @Date 2021-1-11
 * @Description
 * @Version 1.0
 */
@Data
public class Result<T> {

    /**
     * 成功标识
     */
    public final static int SUCCESS_CODE = 0;
    /**
     * 失败标识
     */
    public final static int FAIL_CODE = -1;

    @ApiModelProperty(value = "结果标识")
    private int code;

    @ApiModelProperty(value = "数据信息")
    private T data;

    @ApiModelProperty(value = "结果说明")
    private String msg;

    @ApiModelProperty(value = "响应时间")
    private String time;

    public Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }

    public static <E> Result<E> success(E data, String msg) {
        return new Result<>(SUCCESS_CODE, data, msg);
    }

    public static <E> Result<E> success(E data) {
        return new Result<>(SUCCESS_CODE, data, "调用成功!");
    }

    public static <E> Result<E> success(String msg) {
        return new Result<>(SUCCESS_CODE, null, msg);
    }

    public static <E> Result<E> fail(String msg) {
        return new Result<>(FAIL_CODE, null, (msg == null || msg.isEmpty()) ? "调用失败!" : msg);
    }

}
