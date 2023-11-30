package kim.nzxy.ly.modules.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * MySQL 日志打印 各种类型测试
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "test_any")
public class TestAny {
    /**
     * id, 数字类型
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字符串
     */
    @TableField(value = "test_varchar")
    private String testVarchar;

    /**
     * JSON
     */
    @TableField(value = "test_json", typeHandler = JacksonTypeHandler.class)
    private ObjectNode testJson;

    /**
     * 字节
     */
    @TableField(value = "test_bit")
    private Boolean testBit;

    /**
     * 文件
     */
    @TableField(value = "test_blob")
    private byte[] testBlob;

    /**
     * 日期类型
     */
    @TableField(value = "test_datetime")
    private LocalDateTime testDatetime;
}