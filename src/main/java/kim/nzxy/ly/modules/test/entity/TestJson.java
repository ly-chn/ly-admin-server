package kim.nzxy.ly.modules.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * test json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "test_json")
public class TestJson {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * json
     */
    @TableField(value = "text_field")
    private String textField;

    /**
     * json
     */
    @TableField(value = "json_field", typeHandler = JacksonTypeHandler.class)
    private ObjectNode jsonField;
}