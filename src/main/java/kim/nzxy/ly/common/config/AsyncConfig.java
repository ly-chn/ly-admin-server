package kim.nzxy.ly.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 一些异步需求
 *
 * @author xuyf
 * @since 2022/8/24 18:03
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
}
