package kim.nzxy.ly.common.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kim.nzxy.ly.common.res.PagingVO;
import kim.nzxy.ly.common.res.Res;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author ly-chn
 * @since 2024/1/24 10:58
 */
@Configuration
public class ApiDocOperationCustomizer {
    @Bean
    public ReturnTypeParser returnTypeParser() {
        return new ReturnTypeParser() {
            @Override
            public Type getReturnType(MethodParameter methodParameter) {
                Type returnType = ReturnTypeParser.super.getReturnType(methodParameter);
                Class<?> parameterType = methodParameter.getParameterType();
                // 资源文件或者已经被包装了, 直接返回
                if (parameterType.isAssignableFrom(Resource.class) || parameterType.isAssignableFrom(Res.class)) {
                    return returnType;
                }
                // 分页特殊处理, 转为PagingVO类
                if (parameterType.isAssignableFrom(Page.class) && returnType instanceof ParameterizedType) {
                    Optional<Type> t = TypeUtils.getTypeArguments((ParameterizedType) returnType)
                            .values().stream().findFirst();
                    Type type = t.orElse(Object.class);
                    return TypeUtils.parameterize(Res.class, TypeUtils.parameterize(PagingVO.class, type));
                }
                // void转为Res<Object>
                if (parameterType.isAssignableFrom(void.class)) {
                    return TypeUtils.parameterize(Res.class, Object.class);
                }
                // 包装Res
                return TypeUtils.parameterize(Res.class, returnType);
            }
        };
    }
}
