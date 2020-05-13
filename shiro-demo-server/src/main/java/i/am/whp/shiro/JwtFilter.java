package i.am.whp.shiro;

import com.alibaba.fastjson.JSON;
import i.am.whp.exception.CommonException;
import io.micrometer.core.instrument.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 执行流程: preHandle->isAccessAllowed->executeLogin
 * filter 中抛出的错误, ControllerAdvice 处理不了
 *
 * @author wuhepeng on 2020/5/13
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static final String TOKEN_HEADER = "Bearer ";

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        log.warn("URL: {}", httpServletRequest.getRequestURL().toString());
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            //token catch 登录认证过程中的所有异常
            log.warn("e: {}, type: {}", e.getMessage(), e.getClass().getName());
            return false;
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String token = getAuthzHeader(request).replace(TOKEN_HEADER, "");
        JwtAuthenticationToken jwt = new JwtAuthenticationToken(token);
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated()) {
            return true;
        } else {
            subject.login(jwt);// login 没抛出错误则表示通过身份认证
            return true;
        }
    }

    /**
     * 继承自  BasicHttpAuthenticationFilter , accessDenied 时会发送 sendChallenge
     * 让客户端重新登录, 然后直接返回, 不会继续执行 doFilter, 所以请求不会进入 DispatchServlet
     * 这里直接构造 401 响应 json
     *
     * @return 是否继续执行
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, Object> responseBody = new HashMap<>(3);
        responseBody.put("code", 401);
        responseBody.put("msg", "token校验失败");
        httpResponse.getWriter().write(JSON.toJSONString(responseBody));
        return false;
    }
}
