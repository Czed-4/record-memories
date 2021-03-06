package top.czed.record.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.czed.record.commons.Result;
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
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.fail("账号密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            User result = userService.login(username, password);
            if (result == null) {
                return Result.fail("账号或密码错误");
            }
            result.setPassword(null);
            result.setRealName(null);
            result.setEmail(null);
            result.setPhone(null);
            return Result.success(result, "登录成功");
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            return Result.fail("账号或密码错误");
        }
    }

    @GetMapping("logout")
    @ApiOperation("用户注销")
    public Result<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success("注销成功");
    }

    @GetMapping("authentication")
    @ApiOperation("身份认证")
    public Result<String> authentication() {
        return Result.success("身份认证成功");
    }

}
