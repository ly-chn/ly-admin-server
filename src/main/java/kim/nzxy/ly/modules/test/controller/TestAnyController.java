package kim.nzxy.ly.modules.test.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kim.nzxy.ly.modules.test.entity.TestAny;
import kim.nzxy.ly.modules.test.mapper.TestAnyMapper;
import kim.nzxy.ly.modules.test.service.TestAnyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


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
    private final TestAnyMapper mapper;

    @GetMapping("log-sql")
    public void test() throws JsonProcessingException {
        TestAny testAny = new TestAny();
        testAny.setId(IdWorker.getId());
        testAny.setTestBlob("你好我是凉云".getBytes());
        testAny.setTestBit(false);
        ObjectMapper objectMapper = new ObjectMapper();
        testAny.setTestJson(objectMapper.readValue("""
                {
                  "a": 1,
                  "b": "2",
                  "c": false,
                  "d": [
                    1,
                    2,
                    3
                  ],
                  "e": null,
                  "f": 1.23
                }""", ObjectNode.class));
        testAny.setTestDatetime(LocalDateTime.now());
        testAny.setTestVarchar("测试字符串");
        mapper.insert(testAny);
    }

}
