package i.am.whp.shiro;

import i.am.whp.domain.RolePermissionRelation;
import i.am.whp.domain.User;
import i.am.whp.domain.UserRoleRelation;
import i.am.whp.enums.PermissionEnum;
import i.am.whp.enums.RoleEnum;
import i.am.whp.service.RolePermissionRelationService;
import i.am.whp.service.UserRoleRelationService;
import i.am.whp.service.UserService;
import i.am.whp.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义域
 *
 * @author wuhepeng
 * @date 2020/5/4
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtAuthenticationToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("doGetAuthenticationInfo认证");
//        String username = authenticationToken.getPrincipal().toString();
//
//        // 检查用户是否存在
//        User user = userService.selectByUsername(username);
//        if (user == null) {
//            throw new UnknownAccountException("Account [" + username + "] user is not exist.");
//        }
//        // 检查用户密码是否正确
//        String password = new String(((UsernamePasswordToken) authenticationToken).getPassword());
//        // 获取加密的密码
//        String salt = HashUtil.getSaltByUsername(username);
//        password = HashUtil.getCryptPassword(password, salt);
//        if (!password.equals(user.getPassword())) {
//            throw new IncorrectCredentialsException("Account [" + username + "] password is error.");
//        }
//        // 检查用户状态
//        if (user.getStatus() != UserStatusEnum.enable) {
//            throw new LockedAccountException("Account [" + username + "] is locked.");
//        }

        String token = (String) authenticationToken.getPrincipal();
        JwtUtil.verify(token);

        // SimpleAuthenticationInfo 参数理论上是 username, password, realm's name
        // 因为使用 jwt 所以 username 和 password 都为 token

        // 返回token
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("doGetAuthorizationInfo授权");
//        String username = principalCollection.getPrimaryPrincipal().toString();
        // 通过token获取用户名
        String username = JwtUtil.getUsername(principalCollection.getPrimaryPrincipal().toString());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获得该用户信息
        User user = userService.selectByUsername(username);
        // 角色信息
        List<UserRoleRelation> userRoleRelations = userRoleRelationService.selectByUserId(user.getId());
        List<Integer> roleIds = userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toList());
        // 权限信息
        List<RolePermissionRelation> rolePermissionRelations = rolePermissionRelationService.selectByRoleIds(roleIds);
        List<Integer> permissionIds = rolePermissionRelations.stream().map(RolePermissionRelation::getPermissionId).collect(Collectors.toList());

        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        Set<String> roleSet = roleIds.stream().map(roleId -> RoleEnum.valueOf(roleId).getRoleString()).collect(Collectors.toSet());
        Set<String> permissionSet = permissionIds.stream().map(permissionId -> PermissionEnum.valueOf(permissionId).getPermissionString()).collect(Collectors.toSet());
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
