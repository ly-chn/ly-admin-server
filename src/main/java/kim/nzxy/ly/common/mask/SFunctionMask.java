package kim.nzxy.ly.common.mask;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.AllArgsConstructor;

import java.lang.invoke.SerializedLambda;

/**
 * 构造mbp的lambda表达式, 用以欺骗mbp
 *
 * @author ly-chn
 * @see com.baomidou.mybatisplus.core.toolkit.support.SFunction
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@AllArgsConstructor
public class SFunctionMask<T> implements SFunction<T, Object> {
    private String fieldName;

    @Override
    public Object apply(T t) {
        return null;
    }

    @SuppressWarnings("unused")
    private SerializedLambda writeReplace() {
        return new SerializedLambda(
                null,
                null,
                null,
                null,
                0,
                null,
                "get" + fieldName,
                null,
                null,
                new Object[0]
        );
    }
}
