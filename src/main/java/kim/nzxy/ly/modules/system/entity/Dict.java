package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import kim.nzxy.ly.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 字典
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dict")
public class Dict extends BaseEntity {
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
}