package kim.nzxy.ly.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 全量父类, id, create_by, update_by, create_time, update_time, deleted
 *
 * @author ly-chn
 */
@FieldNameConstants
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 为true表示已删除
     */
    @TableLogic(value = "false", delval = "true")
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
}
