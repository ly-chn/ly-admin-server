package kim.nzxy.ly.common.runner;

/**
 * SaIgnore 网关适配管理
 * @author ly-chn
 */
public class SaIgnoreGatewayUtil {
    private static final String KEY_PREFIX = "sa-token:ignore:";
    public static String includeKey(String appId) {
        return KEY_PREFIX + "includes:" + appId;
    }

    public static String excludeKey(String appId) {
        return KEY_PREFIX +"excludes:"+appId;
    }

    public static String versionKey(String appId) {
        return KEY_PREFIX + "version:" + appId;
    }
}
