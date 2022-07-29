package kim.nzxy.ly.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * JSON相关
 *
 * @author xuyf
 * @since 2022/7/29 9:17
 */
@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .serializerByType(LocalTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")))
                    .deserializerByType(LocalTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")))
                    .serializerByType(LocalDate.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .deserializerByType(LocalDate.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .serializerByType(Long.class, new ToStringSerializer())
                    .serializerByType(Long.TYPE, new ToStringSerializer());
        };
    }
}
