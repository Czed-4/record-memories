package top.czed.record.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.czed.record.commons.Result;
import top.czed.record.entity.SysUser;
import top.czed.record.service.UserService;

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
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("login")
    @ApiOperation("用户登录")
    public Result<SysUser> login(@RequestBody SysUser user) {
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
            SysUser result;
            // 从缓存中查询是否存在用户
            Object sysUser = redisTemplate.opsForHash().get("SysUser", username);
            if (StringUtils.isEmpty(sysUser)) {
                result = userService.login(username, password);
                if (result == null) {
                    return Result.fail("账号或密码错误");
                }
                // 把用户信息存入缓存
                redisTemplate.opsForHash().put("SysUser", result.getUsername(), JSON.toJSONString(result));
            } else {
                result = JSON.parseObject(sysUser.toString(), SysUser.class);
            }
            result.setPassword(null);
            return Result.success(result, "登录成功");
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            return Result.fail("账号或密码错误");
        }
    }

    @PostMapping("update")
    @ApiOperation("用户修改个人信息")
    public Result<SysUser> update(@RequestBody SysUser user){
        String userId = user.getId();
        if (StringUtils.isEmpty(userId)){
            return Result.fail("用户ID不能为空");
        }
        SysUser result = userService.update(user);
        redisTemplate.opsForHash().put("SysUser",result.getUsername(),JSON.toJSONString(result));
        return Result.success(result);
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
