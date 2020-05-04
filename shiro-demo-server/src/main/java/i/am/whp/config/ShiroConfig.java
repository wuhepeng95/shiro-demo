package i.am.whp.config;

import i.am.whp.realm.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * refs https://shiro.apache.org/spring.html
 *
 * @author wuhepeng
 * @date 2020/5/4
 */
@Configuration
public class ShiroConfig {

    @Bean("shiroFilter")
    @DependsOn({"securityManager"})
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setSecurityManager(securityManager);
        // 如果【没有认证】，自动跳转到登录地址（默认的是login.jsp，所以要自定义)
        filterFactoryBean.setLoginUrl("/toLogin");

        // 【认证成功】后跳转的url，通常不配置，默认转到上一个url
        filterFactoryBean.setSuccessUrl("index");

        // 【认证失败】配置用户没有权限访问资源跳转的页面
        filterFactoryBean.setUnauthorizedUrl("/403");

        // 定义过滤链
        Map<String, String> definitions = new HashMap<>(8);
        definitions.put("/api/login", "anon");
        definitions.put("/toLogin", "anon");
        definitions.put("/**", "authc");
        filterFactoryBean.setFilterChainDefinitionMap(definitions);

        return filterFactoryBean;
    }

    /*
    <!--设置自定义Realm -->
   <property name="realm" ref="shiroDbRealm"/>
   <!--将缓存管理器，交给安全管理器 -->
   <property name="cacheManager" ref="shiroEhcacheManager"/>
   <!--记住密码管理-->
   <property name="rememberMeManager" ref="rememberMeManager"/>
   <property name="sessionManager" ref="sessionManager"/>
    */

    @Bean("securityManager")
    public DefaultSecurityManager defaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
//         Single realm app.  If you have multiple realms, use the 'realms' property instead.
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }

    /**
     * 自定义realm
     *
     * @return
     */
    @Bean("userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /*
    <bean class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
        <property name="staticMethod" value="org.apache.shiro.SecurityUtils.setSecurityManager"/>
        <property name="arguments" ref="securityManager"/>
    </bean>
    */

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager);
        return methodInvokingFactoryBean;
    }

}
