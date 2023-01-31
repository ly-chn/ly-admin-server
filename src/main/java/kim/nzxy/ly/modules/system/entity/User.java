package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author xuyf
 * @since 2022/7/27 14:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 头像文件id
     */
    @TableField(value = "avatar")
    private Long avatar;

    /**
     * 删除状态
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    /**
     * 用户解封时间
     */
    @TableField(value = "block_to")
    private LocalDateTime blockTo;
}