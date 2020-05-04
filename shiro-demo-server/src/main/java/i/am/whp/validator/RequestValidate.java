package i.am.whp.validator;

import i.am.whp.bean.UserLoginBean;
import i.am.whp.exception.ErrorParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * @author wuhepeng
 * @date 2020/5/5
 */
public class RequestValidate {

    public static void validateUserLoginBean(UserLoginBean loginParam) {
        if (StringUtils.isEmpty(loginParam.getUsername())) {
            throw new ErrorParamException(HttpStatus.BAD_REQUEST.value(), "用户名不能为空");
        }
        if (StringUtils.isEmpty(loginParam.getPassword())) {
            throw new ErrorParamException(HttpStatus.BAD_REQUEST.value(), "密码不能为空");
        }
    }
}
