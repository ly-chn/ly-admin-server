package kim.nzxy.ly.modules.system.enums;

import kim.nzxy.ly.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件位置(本地, minio, 七牛云, 阿里等等), 仅仅实现本地以供参考
 *
 * @author xuyf
 * @since 2022/7/27 12:26
 */
@Getter
@AllArgsConstructor
public enum FilePositionEnum implements BaseEnum {
    /**
     * 本地存储
     */
    local
}
