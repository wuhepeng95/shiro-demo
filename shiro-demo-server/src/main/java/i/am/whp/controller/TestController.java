package i.am.whp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuhepeng
 * @date 2020/5/3
 */
@RestController
public class TestController {

    @RequestMapping("ok")
    public String isOk() {
        return "ok";
    }
}
