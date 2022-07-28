package kim.nzxy.ly.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 基础实体, 仅包含id, 用于统一id规则
 *
 * @author xuyf
 * @since 2022/7/26 14:26
 */
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseIdEntity {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
}
