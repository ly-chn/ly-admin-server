package kim.nzxy.ly.modules.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 测试用_学生信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "test_student")
public class TestStudent {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 学号
     */
    @TableField(value = "stu_num")
    private String stuNum;

    /**
     * 性别, 字典: sex
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private LocalDate birthday;

    /**
     * 受表扬次数
     */
    @TableField(value = "star_count")
    private Integer starCount;
}