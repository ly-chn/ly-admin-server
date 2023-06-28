package kim.nzxy.ly.common.annotation;

import kim.nzxy.ly.common.enums.LytEnum;

/**
 * 翻译内容到前端
 *
 * <pre>
 *     class DemoVO{
 *
 *     }
 * </pre>
 *
 * @author ly-chn
 */
public @interface Lyt {
    LytEnum value();

    String field() default "";
}
