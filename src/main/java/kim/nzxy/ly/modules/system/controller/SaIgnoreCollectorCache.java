package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.util.SaFoxUtil;
import kim.nzxy.ly.common.runner.SaIgnoreCollector;
import kim.nzxy.ly.common.runner.SaIgnoreGatewayUtil;

import java.util.*;

public class SaIgnoreCollectorCache {

    private static final Map<String, List<SaIgnoreCollector>> INCLUDE_CACHE = new HashMap<>(16);
    private static final Map<String, List<SaIgnoreCollector>> EXCLUDE_CACHE = new HashMap<>(16);
    private static final Map<String, String> VERSION_CACHE = new HashMap<>(16);
    private static final List<SaIgnoreCollector> EMPTY = Collections.emptyList();

    public static List<SaIgnoreCollector> getIncludeList(String appId) {
        refresh(appId);
        return Objects.requireNonNullElse(INCLUDE_CACHE.get(appId),EMPTY);
    }

    public static List<SaIgnoreCollector> getExcludeList(String appId) {
        refresh(appId);

        return Objects.requireNonNullElse(EXCLUDE_CACHE.get(appId),EMPTY);
    }

    @SuppressWarnings("unchecked")
    private static void refresh(String  appId) {
        SaTokenDao dao = SaManager.getSaTokenDao();
        String version = dao.get(SaIgnoreGatewayUtil.versionKey(appId));
        // 取不到版本, 说明没更新
        if (SaFoxUtil.isEmpty(version)) {
            INCLUDE_CACHE.remove(appId);
            EXCLUDE_CACHE.remove(appId);
            return;
        }
        if (!Objects.equals(VERSION_CACHE.get(appId), version)) {
            VERSION_CACHE.put(appId, version);
            INCLUDE_CACHE.put(appId, (List<SaIgnoreCollector>) dao.getObject(SaIgnoreGatewayUtil.includeKey(appId)));
            EXCLUDE_CACHE.put(appId, (List<SaIgnoreCollector>) dao.getObject(SaIgnoreGatewayUtil.excludeKey(appId)));
        }
    }
}
