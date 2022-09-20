package kim.nzxy.ly.common.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import kim.nzxy.ly.common.annotation.SaSkip;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * sa-token
 *
 * @author ly
 * @see <a href="https://sa-token.dev33.cn/">官方文档</a>
 * @since 2022/7/26 14:03
 */
@Configuration
@Slf4j
public class SaTokenConfig implements WebMvcConfigurer {
    /**
     * 注册Sa-Token的注解拦截器，打开注解式鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                        if (handler instanceof HandlerMethod) {
                            Method method = ((HandlerMethod) handler).getMethod();
                            // 忽略权限验证, 方法或者类上添加此注解, 则不需要权限验证
                            // todo: 日志注入
                            MDC.put("userId", "1234");
                            if (method.isAnnotationPresent(SaSkip.class) || method.getDeclaringClass().isAnnotationPresent(SaSkip.class)) {
                                return true;
                            }
                            StpUtil.checkLogin();
                            MDC.put("userId", StpUtil.getLoginIdAsString());
                            SaStrategy.me.checkMethodAnnotation.accept(method);
                        }
                        return true;
                    }
                })
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/doc/**");
    }
}
