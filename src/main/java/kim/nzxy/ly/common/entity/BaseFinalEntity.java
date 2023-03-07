package kim.nzxy.ly.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 简单实体类, 创建人创建时间, 逻辑删除
 *
 * @author ly-chn
 */
@FieldNameConstants
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseFinalEntity {
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
     * 为true表示已删除
     */
    @TableLogic
    @JsonIgnore
    private Boolean deleted;
}
