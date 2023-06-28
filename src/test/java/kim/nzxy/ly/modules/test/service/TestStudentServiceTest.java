package kim.nzxy.ly.modules.test.service;

import kim.nzxy.ly.modules.test.query.TestStudentQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class TestStudentServiceTest {
    @Autowired
    private TestStudentService service;
    @Test
    void search() {
        service.search(new TestStudentQuery(null, null, null, null, null));
    }
}