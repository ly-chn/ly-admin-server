package kim.nzxy.ly.common.config.excel;

import jakarta.servlet.http.HttpServletResponse;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.RequestContextUtil;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * excel导入导出过程中用到的工具类
 *
 * @author xuyingfa
 */
public class ExcelContextUtil {
    private static final String SUFFIX = ".xlsx";

    /**
     * 为下载文件设置响应头
     *
     * @param response 响应
     * @param filename 文件名
     */
    public static void setDownloadHeader(HttpServletResponse response, String filename) {
        if (!filename.endsWith(SUFFIX)) {
            filename += SUFFIX;
        }
        response.setCharacterEncoding("utf-8");
        filename = URLEncoder.encode(filename, StandardCharsets.UTF_8).replace("\\+", "%20");
        // axios下载时获取文件名
        response.setHeader("filename",filename);
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
}
