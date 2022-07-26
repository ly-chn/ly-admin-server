package kim.nzxy.ly.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 如果方法或者类上标注此注解, 则sa-token将忽略其鉴权
 *
 * @author xuyf
 * @since 2022/7/26 14:18
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SaSkip {
}
