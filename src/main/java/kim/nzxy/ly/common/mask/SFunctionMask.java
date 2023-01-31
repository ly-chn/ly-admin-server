package kim.nzxy.ly.common.mask;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.AllArgsConstructor;

import java.lang.invoke.SerializedLambda;

/**
 * 用于构造mbp的lambda表达式, 欺骗mbp
 *
 * @author ly-chn
 * @see com.baomidou.mybatisplus.core.toolkit.support.SFunction
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@AllArgsConstructor
public class SFunctionMask<T> implements SFunction<T, Object> {
    private String implMethodName;
    private String instantiatedMethodType;

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
                "get" + implMethodName,
                null,
                "LY" + instantiatedMethodType + ";",
                new Object[0]
        );
    }
}
