package kim.nzxy.ly.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目配置
 *
 * @author ly-chn
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
