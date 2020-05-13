package i.am.whp.controller;

import i.am.whp.bean.SimpleResponse;
import i.am.whp.bean.UserLoginRequest;
import i.am.whp.bean.UserRegisterRequest;
import i.am.whp.domain.User;
import i.am.whp.service.UserService;
import i.am.whp.validator.RequestValidate;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhepeng
 * @date 2020/5/3
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public SimpleResponse register(@RequestBody UserRegisterRequest registerRequest) {
        // 参数验证
        RequestValidate.validateUserRegisterRequest(registerRequest);
        // 用户注册
        userService.registerUser(registerRequest);
        return SimpleResponse.builder().code(HttpStatus.OK.value()).msg("注册成功").build();
    }

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody UserLoginRequest loginParam) {
        // 参数验证
        RequestValidate.validateUserLoginBean(loginParam);

//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUsername(), loginParam.getPassword());
//        subject.login(token);
//        if (subject.isAuthenticated()) {
//            //登录成功，修改登录错误次数为0
//            log.info("用户登录成功：用户名：{}", subject.getPrincipal());
//            return SimpleResponse.builder().code(HttpStatus.OK.value()).msg("登录成功").build();
//        }
//        return SimpleResponse.builder().code(HttpStatus.OK.value()).msg("登录成功").build();

        String token = userService.login(loginParam);
        Map<String, Object> response = new HashMap<>(3);
        response.put("code", 200);
        response.put("msg", "登录成功");
        response.put("token", token);
        return response;
    }

    @RequestMapping("/admin/login")
    @RequiresPermissions(value = {"write"})
    @RequiresRoles(value = {"admin"})
    public String adminLogin() {
        return "ok";
    }

    /**
     * 退出登录
     */
    @GetMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        log.info("用户[{}]退出登录", subject.getPrincipal().toString());
        subject.logout();
        return "redirect:http://127.0.0.1:8081";
    }

}
