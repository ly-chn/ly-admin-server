package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dict")
public class Dict {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典名称
     */
    @TableField(value = "dict_name")
    private String dictName;

    /**
     * 字典编码
     */
    @TableField(value = "dict_code")
    private String dictCode;

    /**
     * 字典分类, 见字典dict_type
     */
    @TableField(value = "dict_type")
    private String dictType;

    /**
     * 说明
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 创建人id
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改人id
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 删除状态
     */
    @TableField(value = "deleted")
    private Boolean deleted;
}