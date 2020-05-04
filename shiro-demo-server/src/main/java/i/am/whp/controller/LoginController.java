package i.am.whp.controller;

import i.am.whp.bean.SimpleResponse;
import i.am.whp.bean.UserLoginBean;
import i.am.whp.domain.User;
import i.am.whp.validator.RequestValidate;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuhepeng
 * @date 2020/5/3
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

    @PostMapping("login")
    public SimpleResponse login(@RequestBody UserLoginBean loginParam) {
        // 参数验证
        RequestValidate.validateUserLoginBean(loginParam);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUsername(), loginParam.getPassword());
        subject.login(token);
        if (subject.isAuthenticated()) {
            //登录成功，修改登录错误次数为0
            log.info("用户登录成功：用户名：{}", subject.getPrincipal());
            return SimpleResponse.builder().code(HttpStatus.OK.value()).msg("登录成功").build();
        }
        return SimpleResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg("登录失败").build();
    }

    @RequestMapping("/admin/login")
    public String adminLogin() {
        return "ok";
    }

    /**
     * 退出登录
     */
    @GetMapping(value = "/logOut")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        subject.logout();
        log.info("用户[{}]退出登录", user.getUsername());
        return "redirect:http://127.0.0.1:8081";
    }

}
