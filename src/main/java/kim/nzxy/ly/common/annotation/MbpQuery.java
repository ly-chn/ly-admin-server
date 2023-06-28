package kim.nzxy.ly.common.annotation;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.enums.QueryTypeEnum;
import kim.nzxy.ly.common.util.MbpUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * mybatis plus query 检索条件生成
 *
 * @see MbpUtil#buildSearch(ServiceImpl, Object, boolean)
 * @author ly-chn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MbpQuery {
    QueryTypeEnum type();
}
