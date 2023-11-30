package kim.nzxy.ly.modules.test.service;

import kim.nzxy.ly.modules.test.mapper.TestAnyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class TestAnyServiceTest {
    @Autowired
    private TestAnyMapper mapper;

    @Test
    void search() {

    }
}