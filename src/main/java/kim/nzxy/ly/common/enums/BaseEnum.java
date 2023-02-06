package kim.nzxy.ly.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 所有枚举一律使用字符串形式
 *
 * @author ly-chn
 */
public interface BaseEnum extends IEnum<String> {
    /**
     * web格式转换, 以及数据库中的枚举转换
     *
     * @return 字面值
     */
    @Override
    default String getValue() {
        return this.toString();
    }
}
