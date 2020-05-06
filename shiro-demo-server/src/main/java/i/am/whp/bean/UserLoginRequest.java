package i.am.whp.bean;

import lombok.Data;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * @author wuhepeng
 * @date 2020/5/4
 * @see FormAuthenticationFilter
 */
@Data
public class UserLoginRequest {
    private String username;
    private String password;
    private String rememberMe;
}
