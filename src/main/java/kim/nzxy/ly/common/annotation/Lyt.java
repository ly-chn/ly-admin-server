package kim.nzxy.ly.common.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kim.nzxy.ly.common.config.LytJsonSerializer;
import kim.nzxy.ly.common.enums.LytEnum;

import java.lang.annotation.*;

/**
 * 翻译内容到前端
 *
 * <pre>
 * class DemoVO{
 *      &#64;Lyt(LytEnum.NICKNAME, field = "nickname")
 *      private Serializable userId;
 * }
 * </pre>
 *
 * @author ly-chn
 */
@Documented
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = LytJsonSerializer.class)
public @interface Lyt {
    LytEnum value();

    String field() default "";
}
