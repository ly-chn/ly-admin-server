package kim.nzxy.ly.common.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.NotNull;
import kim.nzxy.ly.common.res.Res;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ly-chn
 * @since 2024/1/23 15:22
 */
@RestControllerAdvice
public class ResAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getContainingClass().getPackageName().startsWith("kim.nzxy.ly");
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NotNull MethodParameter returnType,
                                  @NotNull MediaType selectedContentType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request,
                                  @NotNull ServerHttpResponse response) {
        if (body instanceof Res<?> || !selectedContentType.equals(MediaType.APPLICATION_JSON)) {
            return body;
        }
        if (body instanceof Page<?>) {
            // 分页有特殊处理
            return Res.page((Page<?>)body);
        }
        return Res.ok(body);
    }
}
