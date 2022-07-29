package kim.nzxy.ly.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 全量父类, id, create_by, update_by, create_time, update_time, deleted
 *
 * @author xuyf
 * @since 2022/7/26 14:28
 */
@FieldNameConstants
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class BaseFullEntity extends BaseSimpleEntity {

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
}
