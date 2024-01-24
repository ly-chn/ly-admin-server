package kim.nzxy.ly.common.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ly-chn
 * @since 2024/1/23 17:52
 */
@Configuration
public class StringHttpMessageConvertRemover implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(it -> it instanceof StringHttpMessageConverter);
    }
}
