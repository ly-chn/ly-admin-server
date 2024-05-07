package kim.nzxy.ly.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 翻译类型
 *
 * @author ly-chn
 */
@AllArgsConstructor
@Getter
public enum LytEnum {
    /**
     * 用户id->用户昵称(String)
     */
    NICKNAME(Serializable.class, String.class);
    /**
     * 可翻译的字段类型, 用于字段类型检测
     */
    private final Class<?> inType;
    /**
     * 输出的字段类型, 用于明确字段类型
     */
    private final Class<?> outType;
    /**
     * 新增字段时的默认字段名
     */
    private final String defaultFieldName = "nickname";
}
