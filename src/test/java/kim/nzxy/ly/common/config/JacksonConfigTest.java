package kim.nzxy.ly.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author ly-chn
 * @since 2023/12/26 14:42
 */

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class JacksonConfigTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Data
    static class TestTime{
        LocalDateTime localDateTime;
        LocalDate localDate;
        LocalTime localTime;
    }

    @Test
    void localDateTime() throws JsonProcessingException {
        TestTime testTime = objectMapper.readValue("""
                {
                  "localDateTime":"2023-10-13T07:28:25",
                  "localTime": "07:28:25",
                  "localDate": "2023-10-13"
                }""", TestTime.class);
        System.out.println(testTime);
    }
}