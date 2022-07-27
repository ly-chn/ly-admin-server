package kim.nzxy.ly.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
    private FileConfig file;

    @Data
    public static class FileConfig {
        private String uploadPath;
    }
}
