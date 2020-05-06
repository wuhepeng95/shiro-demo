package i.am.whp.validator;

import i.am.whp.bean.UserLoginRequest;
import i.am.whp.bean.UserRegisterRequest;
import i.am.whp.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * @author wuhepeng
 * @date 2020/5/5
 */
public class RequestValidate {

    public static void validateUserLoginBean(UserLoginRequest loginParam) {
        if (StringUtils.isEmpty(loginParam.getUsername())) {
            throw new CommonException(HttpStatus.BAD_REQUEST.value(), "用户名不能为空");
        }
        if (StringUtils.isEmpty(loginParam.getPassword())) {
            throw new CommonException(HttpStatus.BAD_REQUEST.value(), "密码不能为空");
        }
    }

    public static void validateUserRegisterRequest(UserRegisterRequest registerRequest) {
        if (StringUtils.isEmpty(registerRequest.getUsername())) {
            throw new CommonException(HttpStatus.BAD_REQUEST.value(), "用户名不能为空");
        }
        if (StringUtils.isEmpty(registerRequest.getPassword())) {
            throw new CommonException(HttpStatus.BAD_REQUEST.value(), "密码不能为空");
        }
    }
}
