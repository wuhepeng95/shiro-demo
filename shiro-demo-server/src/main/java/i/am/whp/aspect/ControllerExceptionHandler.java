package i.am.whp.aspect;

import i.am.whp.exception.ErrorParamException;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一的controller异常处理器
 *
 * @author wuhepeng
 * @date 2020/5/4
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map handleException() {
        HashMap map = new HashMap<String, Object>(2);
        map.put("code", 500);
        map.put("msg", "服务异常");
        return map;
    }

    @ExceptionHandler(ErrorParamException.class)
    @ResponseBody
    public Map handleException(ErrorParamException e) {
        HashMap map = new HashMap<String, Object>(2);
        map.put("code", e.getErrorCode());
        map.put("msg", e.getMsg());
        return map;
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Map handleException(ShiroException e) {
        HashMap map = new HashMap<String, Object>(2);
        map.put("code", 500);
        map.put("msg", e.getMessage());
        return map;
    }
}
