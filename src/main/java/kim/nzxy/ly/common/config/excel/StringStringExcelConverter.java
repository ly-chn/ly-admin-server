package kim.nzxy.ly.common.config.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import kim.nzxy.ly.common.annotation.LyDict;
import kim.nzxy.ly.common.util.DictUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * @author xuyingfa
 */
@Slf4j
public class StringStringExcelConverter implements Converter<String> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
        String value = context.getValue();
        if (StringUtils.isEmpty(value)) {
            return new WriteCellData<>(value);
        }
        Field field = context.getContentProperty().getField();
        if (!field.isAnnotationPresent(LyDict.class)) {
            return new WriteCellData<>(value);
        }
        return new WriteCellData<>(DictUtil.toLabel(field.getAnnotation(LyDict.class).value(), value));
    }

    @Override
    public String convertToJavaData(ReadConverterContext<?> context) {
        String excelValue = context.getReadCellData().getStringValue();
        if (StringUtils.isEmpty(excelValue)) {
            return excelValue;
        }
        Field field = context.getContentProperty().getField();
        if (!field.isAnnotationPresent(LyDict.class)) {
            return excelValue;
        }
        return DictUtil.toValue(field.getAnnotation(LyDict.class).value(), excelValue);
    }
}
