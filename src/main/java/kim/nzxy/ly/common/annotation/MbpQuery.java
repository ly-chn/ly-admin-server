package kim.nzxy.ly.common.annotation;

import kim.nzxy.ly.common.enums.QueryTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * mybatis plus query 检索条件生成
 *
 * @author xuyf
 * @since 2022/7/26 14:18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.RECORD_COMPONENT})
public @interface MbpQuery {
    QueryTypeEnum type();
}
