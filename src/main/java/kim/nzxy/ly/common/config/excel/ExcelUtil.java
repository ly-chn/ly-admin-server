package kim.nzxy.ly.common.config.excel;


import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.RequestContextUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * excel工具
 *
 * @author xuyf
 * @since 2022/10/21 19:07
 */
@Slf4j
public class ExcelUtil {
    /**
     * 导出到Excel, 并自动完成后续下载操作
     * @param filename 文件名称, 不必填写后缀
     * @param sheetName sheet页名称
     * @param pojoClass 对应java类
     * @param dataset 数据集
     */
    public static void write(String filename, String sheetName, Class<?> pojoClass, Collection<?> dataset) {
        HttpServletResponse response = RequestContextUtil.getResponse();
        try {
            ExcelContextUtil.setDownloadHeader(response, filename);
            EasyExcel.write(response.getOutputStream(), pojoClass).sheet(sheetName)
                    .registerConverter(new StringStringExcelConverter())
                    .doWrite(dataset);
        } catch (IOException e) {
            throw new LyException.Panic("文件导出失败", e);
        }
    }


    /**
     * 导入, 标题行默认为1
     *
     * @param file      文件
     * @param pojoClass 实体类
     * @param consumer  消费数据, 执行SQL逻辑或其他逻辑等等,
     *                  如果抛出LyException异常, 则异常message将作为Excel导入失败原因
     *                  否则为未知异常导致导入失败
     * @param <T>       对应类型
     */
    public static <T> void read(@NotNull MultipartFile file, @NotNull Class<T> pojoClass, @NotNull Consumer<T> consumer) {
        read(file, pojoClass, consumer, 1);
    }

    /**
     * 导入
     *
     * @param file            文件
     * @param pojoClass       实体类
     * @param consumer        消费数据, 执行SQL逻辑或其他逻辑等等,
     *                        如果抛出LyException异常, 则异常message将作为Excel导入失败原因
     *                        否则为未知异常导致导入失败
     * @param titleLineNumber 标题所在行, 从1开始
     * @param <T>             对应类型
     */
    public static <T> void read(@NotNull MultipartFile file,
                                @NotNull Class<T> pojoClass,
                                @NotNull Consumer<T> consumer,
                                @NotNull Integer titleLineNumber) {
        try {
            ExcelImportListener<T> listener = new ExcelImportListener<>(consumer);
            @Cleanup InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, pojoClass, listener)
                    .headRowNumber(titleLineNumber)
                    .registerConverter(new StringStringExcelConverter())
                    .sheet().doRead();
            List<ExcelLineResult<T>> resultList = listener.getExcelLineResultList();
            boolean allSuccess = resultList.stream()
                    .allMatch(it -> it.getViolation().isEmpty() && Objects.isNull(it.getBizError()));
            if (allSuccess) {
                log.info("Excel 数据已全部导入: {}", resultList);
                return;
            }
            log.error("Excel校验失败, 读取结果: {}", resultList);
            HttpServletResponse response = RequestContextUtil.getResponse();
            InputStream templateIs = file.getInputStream();
            ExcelContextUtil.setDownloadHeader(response, "文件导入失败.xlsx");

            EasyExcel.write(response.getOutputStream(), pojoClass)
                    .withTemplate(templateIs)
                    .autoCloseStream(false)
                    .registerWriteHandler(new ExcelErrorFillHandler<T>(resultList, titleLineNumber))
                    .needHead(false)
                    .sheet()
                    .doWrite(Collections.emptyList());
        } catch (Exception e) {
            log.error("文件读取失败", e);
            throw new LyException.Normal("文件读取失败, 请检查文件格式");
        }
        throw new LyException.None();
    }
}
