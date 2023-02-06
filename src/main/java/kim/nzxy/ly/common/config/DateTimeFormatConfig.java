package kim.nzxy.ly.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

/**
 * request parameter date time format
 *
 * @author ly-chn
 */
@Configuration
public class DateTimeFormatConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        registrar.setTimeFormatter(DateTimeFormatter.ISO_LOCAL_TIME);
        registrar.setDateFormatter(DateTimeFormatter.ISO_LOCAL_DATE);
        registrar.registerFormatters(registry);
    }
}
