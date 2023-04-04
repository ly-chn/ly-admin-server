package kim.nzxy.ly.common.runner;

import cn.dev33.satoken.router.SaRouter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaIgnoreCollector {
    /**
     * 请求方式
     */
    private Set<RequestMethod> methods;
    /**
     * 路由规则
     */
    private List<String> patterns;

    public boolean methodMatch(RequestMethod method) {
        return Objects.isNull(methods) || methods.isEmpty() || methods.contains(method);
    }

    public boolean pathMatch(String path) {
        return SaRouter.isMatch(patterns, path);
    }

    public boolean match(RequestMethod method, String path) {
        return methodMatch(method) && pathMatch(path);
    }
}
