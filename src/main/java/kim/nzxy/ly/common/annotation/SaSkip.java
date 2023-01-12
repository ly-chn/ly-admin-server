package kim.nzxy.ly.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如果方法或者类上标注此注解, 则sa-token将忽略其鉴权
 * todo: sa-token已支持
 *
 * @author xuyf
 * @since 2022/7/26 14:18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
public @interface SaSkip {
}
