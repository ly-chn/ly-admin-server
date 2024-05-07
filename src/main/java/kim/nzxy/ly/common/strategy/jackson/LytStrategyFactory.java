package kim.nzxy.ly.common.strategy.jackson;

import kim.nzxy.ly.common.enums.LytEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @author ly-chn
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LytStrategyFactory {
    private static final HashMap<LytEnum, LytHandler<?, ?>> STRATEGY = new HashMap<>();

    /**
     * 逻辑注册
     *
     * @param lytEnum 登录类型
     * @param handler 处理逻辑
     */
    public static void register(LytEnum lytEnum, LytHandler<?, ?> handler) {
        STRATEGY.put(lytEnum, handler);
    }


    /**
     * 获取策略
     */
    public static LytHandler<?, ?> getStrategy(LytEnum lytEnum) {
        return STRATEGY.get(lytEnum);
    }
}
