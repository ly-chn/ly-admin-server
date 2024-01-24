package kim.nzxy.ly.modules.test.controller;

import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.ContentType;
import kim.nzxy.ly.common.res.Res;
import kim.nzxy.ly.modules.test.entity.TestAny;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author ly-chn
 * @since 2024/1/23 17:38
 */
@RestController
@RequestMapping("advice")
public class TestAdvice {
    @GetMapping("str")
    public String testStr() {
        return "test";
    }

    @GetMapping("void")
    public void testVoid() {
    }

    @GetMapping("int")
    public int testInt() {
        return 1;
    }

    @GetMapping("obj")
    public TestAny testObject() {
        TestAny r = new TestAny();
        r.setTestDatetime(LocalDateTime.now());
        r.setTestBlob("1234".getBytes());
        r.setId(1L);
        r.setTestBit(false);
        return r;
    }

    @GetMapping("file")
    public ClassPathResource testResource() {
        return new ClassPathResource("application1.yml");
    }

    @GetMapping("res")
    public Res<Object> testCommonRes() {
        return Res.ok();
    }

    @GetMapping("json-entity")
    public ResponseEntity<TestAny> testJsonEntity() {
        return ResponseEntity.ok(new TestAny());
    }

    @GetMapping("resource-entity")
    public ResponseEntity<ClassPathResource> testResourceEntity() {
        return ResponseEntity.ok(new ClassPathResource("application1.yml"));
    }

    @GetMapping("void-with-byte")
    public void testVoidWithByte(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=application.yml");
        ClassPathResource resource = new ClassPathResource("application.yml");
        response.getOutputStream().write(resource.getContentAsByteArray());
    }
}
