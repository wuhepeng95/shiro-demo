package i.am.whp.service.impl;

import i.am.whp.bean.UserLoginRequest;
import i.am.whp.bean.UserRegisterRequest;
import i.am.whp.dao.UserMapper;
import i.am.whp.domain.User;
import i.am.whp.enums.UserStatusEnum;
import i.am.whp.exception.CommonException;
import i.am.whp.service.UserService;
import i.am.whp.util.HashUtil;
import i.am.whp.util.JwtUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wuhepeng
 * @date 2020/5/4
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserService self;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserRegisterRequest registerRequest) {
        User exist = selectByUsername(registerRequest.getUsername());
        if (exist != null) {
            throw new CommonException(HttpStatus.BAD_REQUEST.value(), "用户名已注册");
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());

        // 根据用户名获取盐值：通常盐值跟用户唯一不可以变更信息挂钩
        String salt = HashUtil.getSaltByUsername(registerRequest.getUsername());
        newUser.setSalt(salt);
        // 密码根据原密码、salt进行md5加密
        newUser.setPassword(HashUtil.getCryptPassword(registerRequest.getPassword(), salt));
        newUser.setStatus(UserStatusEnum.enable);
        newUser.setCreateTime(new Date());
        newUser.setUpdateTime(new Date());

        self.insert(newUser);
    }

    @Override
    public String login(UserLoginRequest registerRequest) {
        String username = registerRequest.getUsername();
        // 检查用户是否存在
        User user = selectByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("Account [" + username + "] user is not exist.");
        }
        // 检查用户密码是否正确
        String password =registerRequest.getPassword();
        // 获取加密的密码
        String salt = HashUtil.getSaltByUsername(username);
        password = HashUtil.getCryptPassword(password, salt);
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("Account [" + username + "] password is error.");
        }
        // 检查用户状态
        if (user.getStatus() != UserStatusEnum.enable) {
            throw new LockedAccountException("Account [" + username + "] is locked.");
        }
        return JwtUtil.createToken(username);
    }

}
