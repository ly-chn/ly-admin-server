package kim.nzxy.ly.common.runner;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import kim.nzxy.ly.common.util.SpringContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ly-chn
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SaTokenSaIgnoreCollectRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        RequestMappingInfoHandlerMapping mapping = SpringContextUtil.getBean("requestMappingHandlerMapping", RequestMappingInfoHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        List<SaIgnoreCollector> includeList = new ArrayList<>();
        handlerMethods.forEach((info, method) -> {
            if (hasIgnored(method)) {
                // noinspection DataFlowIssue
                includeList.add(new SaIgnoreCollector(info.getMethodsCondition().getMethods(),
                        info.getPathPatternsCondition().getPatterns().stream()
                                .map(PathPattern::getPatternString).collect(Collectors.toList())));
            }
        });
        String appId = SpringContextUtil.getId();
        if (includeList.stream().anyMatch(this::hasAntStylePath)) {
            List<SaIgnoreCollector> excludeList = new ArrayList<>();
            handlerMethods.forEach((info, method) -> {
                if (!method.hasMethodAnnotation(SaIgnore.class)) {
                    // noinspection DataFlowIssue
                    excludeList.add(new SaIgnoreCollector(info.getMethodsCondition().getMethods(),
                            info.getPathPatternsCondition().getPatterns().stream()
                                    .map(PathPattern::getPatternString).collect(Collectors.toList())));
                }
            });
            SaManager.getSaTokenDao().setObject(SaIgnoreGatewayUtil.excludeKey(appId), excludeList, -1);
        }
        SaManager.getSaTokenDao().setObject(SaIgnoreGatewayUtil.includeKey(appId), includeList, -1);
        SaManager.getSaTokenDao().setObject(SaIgnoreGatewayUtil.versionKey(appId), UUID.randomUUID().toString(), -1);

    }

    private boolean hasAntStylePath(SaIgnoreCollector collector) {
        String s = collector.getPatterns().toString();
        return s.contains("?") || s.contains("*");
    }

    private boolean hasIgnored(HandlerMethod method) {
        return method.hasMethodAnnotation(SaIgnore.class)|| AnnotatedElementUtils.isAnnotated(method.getBeanType(), SaIgnore.class);
    }
}
