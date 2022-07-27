package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件授权
 *
 * @author xuyf
 * @since 2022/7/27 14:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_file_auth")
public class FileAuth {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文件id
     */
    @TableField(value = "file_record_id")
    private Long fileRecordId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;
}