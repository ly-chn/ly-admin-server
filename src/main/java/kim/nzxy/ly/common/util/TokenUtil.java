package kim.nzxy.ly.common.util;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;

/**
 * 鉴权相关工具类
 *
 * @author ly-chn
 */
public class TokenUtil {
    /**
     * @return 获取当前用户id
     */
    public static Long getLoginId() {
        return StpUtil.getLoginIdAsLong();
    }

    public static SaTokenInfo login(Long id) {
        StpUtil.login(id);
        return StpUtil.getTokenInfo();
    }

    public static void logout() {
        StpUtil.logout();
    }
}
