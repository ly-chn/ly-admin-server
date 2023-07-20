package kim.nzxy.ly.common.config.excel;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.context.SheetWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import jakarta.validation.ConstraintViolation;
import kim.nzxy.ly.common.util.LyValidationUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xuyingfa
 */
@Slf4j
@RequiredArgsConstructor
public class ExcelErrorFillHandler<T> implements SheetWriteHandler, RowWriteHandler {
    /**
     * 错误结果集
     */
    private final List<ExcelLineResult<T>> resultList;
    /**
     * 标题所在行, 从1开始
     */
    private final Integer titleLineNumber;

    private int errorColNum;


    private static void setCellStyle(Cell cell) {
        Workbook workbook = cell.getSheet().getWorkbook();

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(IndexedColors.RED.getIndex());
        style.setFont(font);
        cell.setCellStyle(style);
    }

    @Override
    public void afterSheetCreate(SheetWriteHandlerContext context) {
        SheetWriteHandler.super.afterSheetCreate(context);
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet cachedSheet = writeSheetHolder.getCachedSheet();
        for (int i = 1; i <= cachedSheet.getLastRowNum() + 1; i++) {
            // 空白数据, 不做处理
            if (i < titleLineNumber) {
                continue;
            }
            Row row = cachedSheet.getRow(i - 1);
            // 标题行, 创建标题
            if (i == titleLineNumber) {
                this.errorColNum = row.getLastCellNum();
                row.createCell(row.getLastCellNum(), CellType.STRING).setCellValue("错误信息");
                continue;
            }
            // 错误行
            Cell cell = row.createCell(this.errorColNum, CellType.STRING);
            setCellStyle(cell);
            cell.setCellValue(convertErrMsg(resultList.get(i - titleLineNumber - 1).getViolation()));
        }
    }

    private String convertErrMsg(Set<ConstraintViolation<T>> violation) {
        // 不存在错误信息, 直接返回null
        if (violation.isEmpty()) {
            return null;
        }
        return violation.stream().map(LyValidationUtil::getMessage).collect(Collectors.joining(";\n"));
    }

}
