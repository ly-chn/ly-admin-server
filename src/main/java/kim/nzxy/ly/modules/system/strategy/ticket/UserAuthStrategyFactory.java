package kim.nzxy.ly.modules.system.strategy.ticket;

import kim.nzxy.ly.modules.system.enums.UserAuthTypeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * 账号授权相关策略
 *
 * @author ly-chn
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAuthStrategyFactory {
    private static final HashMap<UserAuthTypeEnum, UserAuthHandler> STRATEGY = new HashMap<>();

    /**
     * 逻辑注册
     *
     * @param authTypeEnum 登录类型
     * @param handler      处理逻辑
     */
    public static void register(UserAuthTypeEnum authTypeEnum, UserAuthHandler handler) {
        STRATEGY.put(authTypeEnum, handler);
    }


    /**
     * 获取策略
     */
    public static UserAuthHandler getStrategy(UserAuthTypeEnum authTypeEnum) {
        return STRATEGY.get(authTypeEnum);
    }
}
