package kim.nzxy.ly.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Statement;
import java.util.Collection;

/**
 * mybatis sql 日志拦截器, 用于打印SQL
 *
 * @author ly-chn
 */
@Slf4j
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class MybatisLogSqlInterceptor implements Interceptor {
    private static <T> T metaValue(Object target, String name) {
        if (Proxy.isProxyClass(target.getClass())) {
            return metaValue(SystemMetaObject.forObject(target).getValue(name), name);
        }
        // noinspection unchecked
        return (T) target;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        int lines = 1;
        String status = "failed";
        try {
            Object proceed = invocation.proceed();
            if (proceed instanceof Collection<?>) {
                lines = ((Collection<?>) proceed).size();
            }
            status = "succeeded";
            return proceed;
        } finally {
            try (Statement statement = metaValue(invocation.getArgs()[0], "h.statement")) {
                String sql = statement.toString();
                sql = sql.substring(sql.indexOf(':'));
                long cost = System.currentTimeMillis() - startTime;
                log.info("sql exec {} took {}ms rest {}rows: {}", status, cost, lines, compressSql(sql));
            } catch (Exception e) {
                log.error("解析sql异常", e);
            }
        }
    }

    private static String compressSql(String sql) {
        StringBuilder sb = new StringBuilder();
        boolean inString = false;
        boolean hasEscape = false;
        boolean hasAppendBlank = false;
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (c == '\'' && !hasEscape) {
                inString = !inString;
            }
            hasEscape = c == '\\' && !hasEscape;
            if (inString) {
                if (c == '\n') {
                    sb.append("\\n");
                } else {
                    sb.append(c);
                }
                continue;
            }
            if (Character.isWhitespace(c)) {
                if (!hasAppendBlank) {
                    sb.append(' ');
                    hasAppendBlank = true;
                }
            } else {
                sb.append(c);
                hasAppendBlank = false;
            }
        }
        return sb.toString().trim();
    }
}