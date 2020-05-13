package i.am.whp.shiro;

import lombok.ToString;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义的身份认证 token, 通过 JWT 实现
 * jwt 是经过身份验证之后产生的令牌, 后面只需要验证 jwt 是否有效即可
 * @author wuhepeng on 2020/5/13
 */
@ToString
public class JwtAuthenticationToken implements AuthenticationToken {

    private String token;

    public JwtAuthenticationToken(String token) {
        this.token = token;
    }

    /**
     * 身份: 简单理解为 username, 这里使用包含 username 的 jwt 代替, 具体参考接口方法的注释
     */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /**
     * 凭证: 简单理解为密码, 具体参考接口方法的注释
     *
     * @return 密码, 证明当前用户是 username 所表示的这个用户
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
