package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import kim.nzxy.ly.modules.system.enums.FilePositionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 文件
 *
 * @author xuyf
 * @since 2022/7/27 16:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_file_record")
@Accessors(chain = true)
public class FileRecord {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * hash
     */
    @TableField(value = "hash")
    private String hash;

    /**
     * 是否锁定, 锁定后需要授权才能读取文件
     */
    @TableField(value = "block")
    private Boolean block;

    /**
     * 原始文件名
     */
    @TableField(value = "filename")
    private String filename;

    /**
     * 后缀
     */
    @TableField(value = "ext")
    private String ext;

    /**
     * 文件存储方式, 本地, 各OSS...
     */
    @TableField(value = "`position`")
    private FilePositionEnum position;

    /**
     * 文件位置, 需要与文件存储类型配合使用, 如本地文件是相对路径, 云文件可能是url
     */
    @TableField(value = "`locator`")
    private String locator;

    /**
     * 创建人id
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 删除状态
     */
    @TableField(value = "deleted")
    private Boolean deleted;
}