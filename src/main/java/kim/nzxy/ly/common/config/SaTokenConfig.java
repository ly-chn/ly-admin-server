package kim.nzxy.ly.common.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import kim.nzxy.ly.common.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * sa-token
 *
 * @author ly
 * @see <a href="https://sa-token.dev33.cn/">官方文档</a>
 */
@Configuration
@Slf4j
public class SaTokenConfig implements WebMvcConfigurer {
    /**
     * 注册Sa-Token的注解拦截器，打开注解式鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> MDC.put("userId", StpUtil.getLoginIdAsString())))
                .addPathPatterns("/**")
                .excludePathPatterns("/doc.html", "/webjars/**", "/v3/api-docs/swagger-config", "/error");
    }
}
