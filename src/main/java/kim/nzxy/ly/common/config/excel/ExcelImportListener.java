package kim.nzxy.ly.common.config.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import jakarta.validation.Validator;
import kim.nzxy.ly.common.util.SpringContextUtil;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuyingfa
 */
@AllArgsConstructor
class ExcelImportListener<T> implements ReadListener<T> {
    private final List<ExcelLineResult<T>> excelLineResultList = new ArrayList<>();
    private final Class<T> pojoClass;

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        ExcelLineResult<T> build = ExcelLineResult.<T>builder()
                .rowIndex(analysisContext.readRowHolder().getRowIndex())
                .target(t)
                .build();
        excelLineResultList.add(build);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (excelLineResultList.isEmpty()) {
            return;
        }
        Validator validator = SpringContextUtil.getBean(Validator.class);
        excelLineResultList.forEach(it -> {
            it.setViolation(validator.validate(it.getTarget()));
        });
    }

    public List<ExcelLineResult<T>> getExcelLineResultList() {
        return excelLineResultList;
    }
}
