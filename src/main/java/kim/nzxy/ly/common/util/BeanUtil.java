package kim.nzxy.ly.common.util;

import org.springframework.beans.BeanUtils;

/**
 * bean工具, 期待方案的平替
 * @author ly-chn
 */
public class BeanUtil {
    public static <T> T toBean(Object source, Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(source, t);
        return t;
    }
}
