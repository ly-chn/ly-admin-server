package kim.nzxy.ly.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

/**
 * 全量父类, id, create_by, update_by, create_time, update_time, deleted
 *
 * @author xuyf
 * @since 2022/7/26 14:28
 */
@FieldNameConstants
public abstract class BaseNormalEntity extends BaseIdEntity{
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

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

    /**
     * 为true表示已删除
     */
    @TableField()
    @TableLogic
    @JsonIgnore
    private Boolean deleted;
}
