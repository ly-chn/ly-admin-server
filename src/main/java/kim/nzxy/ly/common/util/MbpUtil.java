package kim.nzxy.ly.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.annotation.MbpQuery;
import kim.nzxy.ly.common.exception.LyException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Optional;

/**
 * mybatis plus工具类
 * 查询条件多的时候一个一个写好麻烦, 这个工具类可以根据注解自动构建查询条件并完成分页查询
 * <i>目前编写过程中, 我对这个工具类的前途不太乐观</i>
 *
 * @author ly-chn
 */
public class MbpUtil {
    /**
     * between字段区间开始标识
     */
    private static final String betweenMin = "Min";
    /**
     * between字段区间结尾标识
     */
    private static final String betweenMax = "Max";

    public static <M extends BaseMapper<T>, T> void buildSearch(ServiceImpl<M, T> service, Object query) {
        service.query();
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        HashMap<Field, Object> fieldValueMap = new HashMap<>();
        for (Field field : FieldUtils.getFieldsWithAnnotation(query.getClass(), MbpQuery.class)) {
            try {
                Object value = FieldUtils.readField(field, query, true);
                if (isInvalid(value)) {
                    continue;
                }
                fieldValueMap.put(field, value);
            } catch (IllegalAccessException ignored) {
            }
        }
        HashMap<Field, Object> betweenFieldValueMap = new HashMap<>();
        // 处理非between字段
        fieldValueMap.forEach((field, value) -> {
            MbpQuery annotation = field.getAnnotation(MbpQuery.class);
            // noinspection AlibabaSwitchStatement
            switch (annotation.type()) {
                case EQ -> queryWrapper.eq(field.getName(), value);
                case NE -> queryWrapper.ne(field.getName(), value);
                case GT -> queryWrapper.gt(field.getName(), value);
                case GE -> queryWrapper.ge(field.getName(), value);
                case LT -> queryWrapper.lt(field.getName(), value);
                case LE -> queryWrapper.le(field.getName(), value);
                case LIKE -> queryWrapper.like(field.getName(), value);
                case NOT_LIKE -> queryWrapper.notLike(field.getName(), value);
                case LIKE_LEFT -> queryWrapper.likeLeft(field.getName(), value);
                case LIKE_RIGHT -> queryWrapper.likeRight(field.getName(), value);
                case NOT_LIKE_LEFT -> queryWrapper.notLikeLeft(field.getName(), value);
                case NOT_LIKE_RIGHT -> queryWrapper.notLikeRight(field.getName(), value);
                case IN -> queryWrapper.in(field.getName(), value);
                case NOT_IN -> queryWrapper.notIn(field.getName(), value);
                case BETWEEN, NOT_BETWEEN -> {
                    betweenFieldValueMap.put(field, value); 
                }
                default -> throw new LyException.Panic("架构支持能力不足");
            }
        });
        betweenFieldValueMap.forEach((field, value) -> {
            if (field.getName().endsWith(betweenMin)) {
                if (isInvalid(value)) {
                    return;
                }
                String fieldName = field.getName().substring(0, field.getName().length() - betweenMin.length());
                String maxFieldName = fieldName + betweenMax;
                Optional<Field> maxFieldOptional = betweenFieldValueMap.keySet().stream()
                        .filter(it -> it.getName().contentEquals(maxFieldName)).findFirst();
                if (maxFieldOptional.isEmpty()) {
                    return;
                }
                Field maxField = maxFieldOptional.get();
                Object maxValue = betweenFieldValueMap.get(maxField);
                if (isInvalid(maxValue)) {
                    return;
                }
                MbpQuery annotation = field.getAnnotation(MbpQuery.class);
                // noinspection AlibabaSwitchStatement
                switch (annotation.type()) {
                    case BETWEEN -> queryWrapper.between(fieldName, value, maxValue);
                    case NOT_BETWEEN -> queryWrapper.notBetween(fieldName, value, maxValue);
                    default -> throw new LyException.Panic("架构支持能力不足");
                }
            }
        });
    }

    /**
     * 判断字段值是否有效
     * @param value 字段值
     * @return true表示无效
     */
    private static boolean isInvalid(Object value) {
        if (value == null) {
            return false;
        }
        return !(value instanceof String) || !StringUtils.isEmpty((String) value);
    }
}
