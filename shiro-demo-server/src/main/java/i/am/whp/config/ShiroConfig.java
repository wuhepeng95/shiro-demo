package i.am.whp.config;

import i.am.whp.shiro.JwtFilter;
import i.am.whp.shiro.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt", new JwtFilter());
        filterFactoryBean.setFilters(filterMap);

        filterFactoryBean.setSecurityManager(securityManager);
        // 如果【没有认证】，自动跳转到登录地址（默认的是login.jsp，所以要自定义)
//        filterFactoryBean.setLoginUrl("/toLogin");

        // 【认证成功】后跳转的url，通常不配置，默认转到上一个url
//        filterFactoryBean.setSuccessUrl("index");

        // 【认证失败】配置用户没有权限访问资源跳转的页面
        filterFactoryBean.setUnauthorizedUrl("/403");

        // 定义过滤链 针对认证
        Map<String, String> definitions = new HashMap<>(8);
        definitions.put("/api/login", "anon");
        definitions.put("/api/register", "anon");
        definitions.put("/toLogin", "anon");
//        definitions.put("/**", "authc");
        definitions.put("/**", "jwt");
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
    public DefaultSecurityManager defaultSecurityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();

//         Single realm app.  If you have multiple realms, use the 'realms' property instead.
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm());
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultSecurityManager.setSubjectDAO(subjectDAO);

        defaultSecurityManager.setRealms(realms);
        return defaultSecurityManager;
    }

    /**
     * 自定义realm
     *
     * @return
     */
    @Bean("userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        // hash方式
//        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        // hash次数
//        hashedCredentialsMatcher.setHashIterations(5);
//        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
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

    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
