package kim.nzxy.ly.common.config.excel;

import jakarta.servlet.http.HttpServletResponse;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.RequestContextUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * excel导入导出过程中用到的工具类
 *
 * @author xuyingfa
 */
public class ExcelContextUtil {
    private static final String SUFFIX = ".xlsx";

    public static InputStream getInputStream(MultipartFile file) {
        if (file == null) {
            throw new LyException.Normal("文件不能为空");
        }
        try {
            return file.getInputStream();
        } catch (IOException e) {
            throw new LyException.Normal("读取文件失败", e);
        }
    }

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
        response.setHeader("Content-Disposition", "attachment; filename=" + RequestContextUtil.filenameEncoding(filename));
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("filename", filename);
    }
}
