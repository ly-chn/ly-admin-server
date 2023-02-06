package kim.nzxy.ly.common.util;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.annotation.MbpQuery;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.mask.SFunctionMask;
import kim.nzxy.ly.common.res.Paging;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * mybatis plus工具类
 * 查询条件多的时候一个一个写好麻烦, 这个工具类可以根据注解自动构建查询条件并完成分页查询
 * <i>目前编写过程中, 我对这个工具类的前途不太乐观</i>
 *
 * @author ly-chn
 */
@SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
public class MbpUtil {
    /**
     * between字段区间开始标识
     */
    private static final String betweenMin = "Min";
    /**
     * between字段区间结尾标识
     */
    private static final String betweenMax = "Max";

    /**
     * 用于快速构建检索条件
     *
     * @param service service对象
     * @param query   检索条件, query对象
     * @param <M>     mapper interface
     * @param <T>     实体类
     * @return 构建完成的检索条件
     */
    public static <M extends BaseMapper<T>, T> LambdaQueryChainWrapper<T> buildSearch(ServiceImpl<M, T> service, Object query) {
        LambdaQueryChainWrapper<T> result = service.lambdaQuery();
        HashMap<Field, Object> fieldValueMap = new HashMap<>();
        for (Field field : FieldUtils.getFieldsListWithAnnotation(query.getClass(), MbpQuery.class)) {
            try {
                Object value = FieldUtils.readField(field, query, true);
                if (Objects.isNull(value) || (value instanceof String && StringUtils.isEmpty((String) value))) {
                    continue;
                }
                fieldValueMap.put(field, value);
            } catch (IllegalAccessException ignored) {
            }
        }
        if (fieldValueMap.isEmpty()) {
            return result;
        }
        HashMap<Field, Object> betweenFieldValueMap = new HashMap<>();
        // 处理非between字段
        fieldValueMap.forEach((field, value) -> {
            MbpQuery annotation = field.getAnnotation(MbpQuery.class);
            // noinspection AlibabaSwitchStatement
            switch (annotation.type()) {
                case EQ -> result.eq(mask(field), value);
                case NE -> result.ne(mask(field), value);
                case GT -> result.gt(mask(field), value);
                case GE -> result.ge(mask(field), value);
                case LT -> result.lt(mask(field), value);
                case LE -> result.le(mask(field), value);
                case LIKE -> result.like(mask(field), value);
                case NOT_LIKE -> result.notLike(mask(field), value);
                case LIKE_LEFT -> result.likeLeft(mask(field), value);
                case LIKE_RIGHT -> result.likeRight(mask(field), value);
                case NOT_LIKE_LEFT -> result.notLikeLeft(mask(field), value);
                case NOT_LIKE_RIGHT -> result.notLikeRight(mask(field), value);
                case IN -> result.in(mask(field), value);
                case NOT_IN -> result.notIn(mask(field), value);
                case BETWEEN, NOT_BETWEEN -> betweenFieldValueMap.put(field, value);
                default -> throw new LyException.Panic("架构支持能力不足");
            }
        });
        // 处理between字段
        betweenFieldValueMap.forEach((field, value) -> {
            if (field.getName().endsWith(betweenMin)) {
                String fieldName = field.getName().substring(0, field.getName().length() - betweenMin.length());
                String maxFieldName = fieldName + betweenMax;
                Optional<Field> maxFieldOptional = betweenFieldValueMap.keySet().stream()
                        .filter(it -> it.getName().contentEquals(maxFieldName)).findFirst();
                if (maxFieldOptional.isEmpty()) {
                    return;
                }
                Field maxField = maxFieldOptional.get();
                Object maxValue = betweenFieldValueMap.get(maxField);
                MbpQuery annotation = field.getAnnotation(MbpQuery.class);
                // noinspection AlibabaSwitchStatement
                switch (annotation.type()) {
                    case BETWEEN -> result.between(mask(fieldName), value, maxValue);
                    case NOT_BETWEEN -> result.notBetween(mask(fieldName), value, maxValue);
                    default -> throw new LyException.Panic("架构支持能力不足");
                }
            }
        });
        return result;
    }

    /**
     * 快速构建检索条件并分页
     * @param service service对象
     * @param query   检索条件, query对象
     * @param <M>     mapper interface
     * @param <T>     实体类
     * @return 分页结果
     */
    public static <M extends BaseMapper<T>, T> Page<T> page(ServiceImpl<M, T> service, Object query) {
        return buildSearch(service, query).page(Paging.startPage());
    }

    private static <T> SFunctionMask<T> mask(String fieldName) {
        return new SFunctionMask<>(fieldName);
    }

    private static <T> SFunctionMask<T> mask(Field field) {
        return mask(field.getName());
    }
}
