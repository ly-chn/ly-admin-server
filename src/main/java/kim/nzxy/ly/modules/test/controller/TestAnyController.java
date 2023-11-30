package kim.nzxy.ly.modules.test.controller;

import kim.nzxy.ly.modules.test.service.TestAnyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试 mybatis 日志打印
 *
 * @author ly-chn
 */
@RestController
@RequestMapping("/test-any")
@RequiredArgsConstructor
@Slf4j
public class TestAnyController {
    /**
     * 服务对象
     */
    private final TestAnyService service;

    public void test() {

    }

}
