package kim.nzxy.ly.common.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 项目配置
 *
 * @author xuyf
 * @since 2022/7/27 16:05
 */
@ConfigurationProperties(prefix = "app")
@Component
@Data
public class AppProperties {
    /**
     * 文件相关配置
     */
    private FileConfig file;

    @Data
    public static class FileConfig {
        /**
         * 本地文件上传时, 上传的路径
         */
        private String uploadPath;
    }
}
