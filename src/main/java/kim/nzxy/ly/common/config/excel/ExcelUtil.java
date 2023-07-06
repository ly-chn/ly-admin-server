package kim.nzxy.ly.common.config.excel;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import jakarta.servlet.http.HttpServletResponse;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.RequestContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param <T>       对应类型
     * @return 成功将返回数据, 否则自动拦截并返回内容
     */
    public static <T> List<T> read(MultipartFile file, Class<T> pojoClass) {
        return read(file, pojoClass, 1);
    }

    /**
     * 导入
     *
     * @param file            文件
     * @param pojoClass       实体类
     * @param titleLineNumber 标题所在行, 从1开始
     * @param <T>             对应类型
     * @return 成功将返回数据, 否则自动拦截并返回内容
     */
    public static <T> List<T> read(MultipartFile file, Class<T> pojoClass, Integer titleLineNumber) {
        ExcelImportListener<T> listener = new ExcelImportListener<>(pojoClass);
        InputStream inputStream = ExcelContextUtil.getInputStream(file);
        EasyExcel.read(inputStream, pojoClass, listener).sheet().doRead();
        List<ExcelLineResult<T>> resultList = listener.getExcelLineResultList();
        // 全部校验通过, 则允许返回
        if (resultList.stream().allMatch(it -> it.getViolation().isEmpty())) {
            return resultList.stream().map(ExcelLineResult::getTarget).collect(Collectors.toList());
        }
        log.error("Excel校验失败, 读取结果: {}", resultList);
        HttpServletResponse response = RequestContextUtil.getResponse();
        try {
            ExcelContextUtil.setDownloadHeader(response, "文件导入失败.xlsx");
            EasyExcel.write(response.getOutputStream(), pojoClass)
                    // file.getInputStream()
                    .withTemplate("C:\\Users\\Liaoliao\\Downloads\\template-fail.xlsx")
                    .autoCloseStream(false)
                    .registerWriteHandler(new ExcelErrorFillHandler<T>(resultList, titleLineNumber))
                    .sheet()
                    .doFill(resultList);
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("关闭流失败", e);
            }
        }
    }
}
