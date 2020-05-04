package i.am.whp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuhepeng
 * @date 2020/5/3
 */
@Controller
public class PageController {

    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:http://127.0.0.1:8081";
    }

    @RequestMapping("index")
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("403")
    @ResponseBody
    public String noAuth() {
        return "403";
    }
}
