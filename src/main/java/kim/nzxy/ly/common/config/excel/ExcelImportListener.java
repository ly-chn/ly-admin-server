package kim.nzxy.ly.common.config.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.SpringContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * excel导入校验
 *
 * @author ly-chn
 */
@Slf4j
@RequiredArgsConstructor
class ExcelImportListener<T> implements ReadListener<T> {
    private final List<ExcelLineResult<T>> excelLineResultList = new ArrayList<>();
    public static String defaultBizError = "未知异常";

    /**
     * 业务处理, 入库, 解析等
     */
    private final Consumer<T> consumer;

    /**
     * 每次读取, 记录读取信息
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        if (log.isDebugEnabled()) {
            log.debug("读取到数据: {}", t);
        }
        ExcelLineResult<T> build = ExcelLineResult.<T>builder()
                .rowIndex(analysisContext.readRowHolder().getRowIndex())
                .target(t)
                .build();
        excelLineResultList.add(build);
    }

    /**
     * 读取完毕后执行校验
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (excelLineResultList.isEmpty()) {
            return;
        }
        Validator validator = SpringContextUtil.getBean(Validator.class);
        excelLineResultList.forEach(it -> {
            Set<ConstraintViolation<T>> validate = validator.validate(it.getTarget());
            it.setViolation(validate);
            // 校验不通过, 不必执行业务逻辑
            if (!validate.isEmpty()) {
                return;
            }
            try {
                consumer.accept(it.getTarget());
            } catch (LyException e) {
                log.error("解析数据失败: {}, 异常信息: {}", it, e.getMessage());
                it.setBizError(e.getMessage());
            } catch (Exception e) {
                log.error("解析数据失败", e);
                it.setBizError(defaultBizError);
            }
        });
    }

    public List<ExcelLineResult<T>> getExcelLineResultList() {
        return excelLineResultList;
    }
}
