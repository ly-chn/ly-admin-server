package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.util.SaFoxUtil;
import kim.nzxy.ly.common.runner.SaIgnoreCollector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@SaIgnore
@RequestMapping("matcher")
public class MatcherController {

    @GetMapping("path")
    public boolean isIgnored(String path, String method) {
        RequestMethod requestMethod = RequestMethod.valueOf(method);
        List<SaIgnoreCollector> excludeList = SaIgnoreCollectorCache.getExcludeList("application");
        if (!excludeList.isEmpty()) {
            boolean unIgnore = excludeList.stream().anyMatch(it -> it.match(requestMethod, path));
            if (unIgnore) {
                return false;
            }
        }
        List<SaIgnoreCollector> includeList = SaIgnoreCollectorCache.getIncludeList("application");
        if (includeList.isEmpty()) {
            return false;
        }
        return includeList.stream().anyMatch(it -> it.match(requestMethod, path));
    }
}
