package kim.nzxy.ly.common.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kim.nzxy.ly.common.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

/**
 * spring上下文相关工具类
 *
 * @author ly-chn
 */
@Slf4j
public class RequestContextUtil {
    /**
     * @return 获取当前请求
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * @return 获取当前响应
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = Optional.ofNullable(RequestContextHolder.getRequestAttributes()).orElseThrow(() -> {
            log.error("非web上下文无法获取请求属性, 异步操作请在同步操作内获取所需信息");
            return new LyException.Panic("请求异常");
        });
        return ((ServletRequestAttributes) attributes);
    }


    /**
     * 文件名编码, 适配Firefox/Chrome/Safari
     *
     * @param filename 文件名
     * @return 编码后的文件名
     */
    public static String filenameEncoding(String filename) {
        String agent = getRequest().getHeader(HttpHeaders.USER_AGENT).toLowerCase();
        if (agent.contains("firefox")) {
            filename = "=?utf-8?B?"
                    + Base64.getEncoder().encodeToString(filename.getBytes(StandardCharsets.UTF_8))
                    + "?=";
        } else if (agent.contains("msie")) {
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        } else if (agent.contains("safari")) {
            filename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        } else {
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        }
        return filename;
    }
}
