package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import kim.nzxy.ly.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
    * 字典项
    */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dict_item")
public class DictItem extends BaseEntity {
    /**
     * 字典id
     */
    @TableField(value = "dict_id")
    private Long dictId;

    /**
     * 字典编码
     */
    @TableField(value = "`label`")
    private String label;

    /**
     * 字典值
     */
    @TableField(value = "`value`")
    private String value;

    /**
     * 当类型为标签时, 展示的样式, 字典: dict_tag_type
     */
    @TableField(value = "tag_type")
    private String tagType;

    /**
     * 排序权重, asc
     */
    @TableField(value = "order_weight")
    private Long orderWeight;

    /**
     * 提示说明, 将显示给用户
     */
    @TableField(value = "tips")
    private String tips;

    /**
     * 说明
     */
    @TableField(value = "remark")
    private String remark;
}