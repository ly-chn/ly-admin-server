package kim.nzxy.ly.common.config.excel;

import jakarta.validation.ConstraintViolation;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


/**
 * Excel按行导入结果
 *
 * @author xuyingfa
 */
@Data
@Builder
class ExcelLineResult<T> {

    /**
     * 行号, 从0开始
     */
    private Integer rowIndex;
    /**
     * 导入的数据
     */
    private T target;
    /**
     * 校验结果
     */
    private Set<ConstraintViolation<T>> violation;
}
