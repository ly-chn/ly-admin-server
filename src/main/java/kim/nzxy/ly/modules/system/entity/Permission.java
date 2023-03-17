package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import kim.nzxy.ly.common.entity.BaseEntity;
import kim.nzxy.ly.common.util.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 权限信息配置
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_permission")
public class Permission extends BaseEntity implements TreeNode<Permission> {
    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 组件名称
     */
    private String componentName;

    /**
     * 重定向(如需)
     */
    private String redirect;

    /**
     * 权限类型, 字典: sys_permission_type
     */
    private String permissionType;

    /**
     * 权限编码
     */
    private String authSymbol;

    /**
     * 显示排序 asc
     */
    private Long orderWeight;

    /**
     * 是否缓存, 字典: sys_if
     */
    private Boolean keepAlive;

    /**
     * 是否隐藏, 字典: sys_if
     */
    private Boolean hidden;

    /**
     * 是否禁用, 字典: sys_if
     */
    private Boolean disabled;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否新标签页打开, 字典: sys_if
     */
    private Boolean createTab;

    @TableField(exist = false)
    private List<Permission> children;
}