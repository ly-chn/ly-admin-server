package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import kim.nzxy.ly.common.entity.BaseFinalEntity;
import kim.nzxy.ly.modules.system.enums.FilePositionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 文件
 *
 * @author ly-chn
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_file_record")
@SuperBuilder
public class FileRecord extends BaseFinalEntity {

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
     * 业务id
     */
    @TableField(value = "biz_id")
    private Long bizId;

    /**
     * 业务路径, [业务表名.业务字段]
     */
    @TableField(value = "biz_path")
    private String bizPath;

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
}