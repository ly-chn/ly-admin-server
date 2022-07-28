package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import kim.nzxy.ly.common.entity.BaseSimpleEntity;
import kim.nzxy.ly.modules.system.enums.FilePositionEnum;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 文件
 *
 * @author xuyf
 * @since 2022/7/27 16:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_file_record")
@SuperBuilder
public class FileRecord extends BaseSimpleEntity {

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
     * 删除状态
     */
    @TableField(value = "deleted")
    private Boolean deleted;
}