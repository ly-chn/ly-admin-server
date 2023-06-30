package kim.nzxy.ly.modules.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TestStudentImportDTO {
    /**
     * 学号
     */
    @ExcelProperty("学号")
    @NotEmpty(message = "学号不能为空")
    private String stuNum;

    /**
     * 性别, 字典: sex
     */
    @ExcelProperty("性别")
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 生日
     */
    @ExcelProperty("生日")
    @NotNull(message = "请填写生日")
    private LocalDate birthday;

    /**
     * 受表扬次数
     */
    @ExcelProperty("受表扬次数")
    private Integer starCount;
}
