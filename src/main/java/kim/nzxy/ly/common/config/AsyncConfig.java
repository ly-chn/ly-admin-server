package kim.nzxy.ly.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 一些异步需求
 *
 * @author ly-chn
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    // todo: 异步配置, 暂无应用场景
}
