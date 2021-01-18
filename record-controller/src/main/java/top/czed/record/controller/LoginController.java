package top.czed.record.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.czed.record.commons.Result;
import top.czed.record.commons.utils.CookieUtil;
import top.czed.record.entity.User;
import top.czed.record.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Czed
 * @Date 2021-1-12
 * @Description
 * @Version 1.0
 */
@RestController
@Api(tags = "登录控制器")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation("用户登录")
    public Result<User> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Result.fail("账号密码不能为空");
        }
        User result = userService.login(username, password);
        if (result == null) {
            return Result.fail("账号或密码错误");
        }
        result.setPassword(null);
        result.setRealName(null);
        result.setEmail(null);
        result.setPhone(null);
        CookieUtil.setCookie(request, response, "user", JSON.toJSONString(result), 8 * 60 * 60, true);
        return Result.success(result, "登陆成功");
    }

}
