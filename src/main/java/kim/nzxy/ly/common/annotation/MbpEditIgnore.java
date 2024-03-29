package kim.nzxy.ly.common.annotation;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 构建新增/修改时忽略该字段
 * @see kim.nzxy.ly.common.util.MbpUtil#edit(ServiceImpl, Object)
 * @author ly-chn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MbpEditIgnore {
}
