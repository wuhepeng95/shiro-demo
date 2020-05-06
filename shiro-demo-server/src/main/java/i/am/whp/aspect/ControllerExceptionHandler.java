package i.am.whp.aspect;

import i.am.whp.bean.SimpleResponse;
import i.am.whp.exception.CommonException;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public SimpleResponse handleException(Exception e) {
        return SimpleResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(e.getMessage()).build();
    }

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public SimpleResponse handleException(CommonException e) {
        return SimpleResponse.builder().code(e.getErrorCode()).msg(e.getMessage()).build();
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public SimpleResponse handleException(ShiroException e) {
        return SimpleResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(e.getMessage()).build();
    }
}
