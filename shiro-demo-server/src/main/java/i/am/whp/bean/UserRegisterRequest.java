package i.am.whp.bean;

import lombok.Data;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
@Data
public class UserRegisterRequest {
    private String username;
    private String password;
}
