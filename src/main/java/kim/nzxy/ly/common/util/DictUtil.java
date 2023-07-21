package kim.nzxy.ly.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 字典工具类
 * todo 实现静态方法字典转换
 *
 * @author ly-chn
 */
@Slf4j
public class DictUtil {
    /**
     * 转换为字典label
     *
     * @param dictCode 字典编码
     * @param value    字典值
     * @return 字典label
     */
    public static String toLabel(String dictCode, String value) {
        return value + "的label";
    }

    /**
     * 转换为字典value
     *
     * @param dictCode 字典编码
     * @param label    字典label
     * @return 字典value
     */
    public static String toValue(String dictCode, String label) {
        return label + "的value";
    }
}
