package kim.nzxy.ly.common.util;

import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.validation.ConstraintViolation;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author ly-chn
 */
public class LyValidationUtil {
    public static String getMessage(ConstraintViolation<?> constraintViolation) {
        String message = constraintViolation.getMessage();
        if (!message.contains("{fieldTitle}")) {
            return message;
        }
        String fieldTitle = "";
        Class<?> rootBeanClass = constraintViolation.getRootBeanClass();
        if (Objects.nonNull(rootBeanClass)) {
            Field field = FieldUtils
                    .getField(rootBeanClass, constraintViolation.getPropertyPath().toString(), true);
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (Objects.nonNull(excelProperty) && excelProperty.value().length != 0) {
                fieldTitle = excelProperty.value()[0];
            }
        }
        return message.replace("{fieldTitle}", fieldTitle);
    }
}
