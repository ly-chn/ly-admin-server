package kim.nzxy.ly.modules.test.mapper;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kim.nzxy.ly.modules.test.entity.TestAny;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class TestAnyMapperTest {
    @Autowired
    private TestAnyMapper mapper;

    @Test
    void search() throws JsonProcessingException {
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