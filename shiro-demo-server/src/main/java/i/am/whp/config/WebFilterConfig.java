package i.am.whp.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @author wuhepeng
 * @date 2020/5/3
 */
@Configuration
public class WebFilterConfig {

//<filter>
//    <filter-name>shiroFilter</filter-name>
//    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
//    <init-param>
//        <param-name>targetFilterLifecycle</param-name>
//        <param-value>true</param-value>
//    </init-param>
//</filter>

    @Bean
    @DependsOn("shiroFilter")
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        // 这个是spring-web的类
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        // 设置bean的生命周期由servlet来管理
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        // targetBeanName 就是这个bean↑
        delegatingFilterProxy.setTargetBeanName("shiroFilter");

        filterRegistrationBean.setFilter(delegatingFilterProxy);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
