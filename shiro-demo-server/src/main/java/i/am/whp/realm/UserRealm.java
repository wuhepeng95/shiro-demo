package i.am.whp.realm;

import i.am.whp.domain.User;
import i.am.whp.enums.UserStatusEnum;
import i.am.whp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义域
 *
 * @author wuhepeng
 * @date 2020/5/4
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("doGetAuthenticationInfo认证");
        String username = authenticationToken.getPrincipal().toString();
        // 检查用户是否存在
        User user = userService.selectByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("Account [" + username + "] user is exist.");
        }
        // 检查用户密码是否正确
        String password = new String(((UsernamePasswordToken) authenticationToken).getPassword());
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("Account [" + username + "] password is error.");
        }
        // 检查用户状态
        if (user.getStatus() != UserStatusEnum.enable) {
            throw new LockedAccountException("Account [" + username + "] is locked.");
        }
        // 返回token
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("doGetAuthorizationInfo授权");
        return null;
    }
}
