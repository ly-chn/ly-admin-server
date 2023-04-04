package kim.nzxy.ly.modules.test.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kim.nzxy.ly.modules.test.entity.TestStudent;
import kim.nzxy.ly.modules.test.query.TestStudentQuery;
import kim.nzxy.ly.modules.test.service.TestStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ly-chn
 */
@RestController
@RequestMapping("/test-student")
@RequiredArgsConstructor
@Slf4j
public class TestStudentController {
    /**
     * 服务对象
     */
    private final TestStudentService service;


    @GetMapping("search")
    public Page<TestStudent> search(TestStudentQuery query) {
        return service.anotherSearch(query);
    }

    @RequestMapping("${sa-token.token-name}")
    @SaIgnore
    public String any() {
        return "any matcher";
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping("{intValue}")
    public String test(@PathVariable Integer intValue) {
        return "int value" + intValue;
    }

    @GetMapping("*")
    public String root() {
        return "root level";
    }
    @GetMapping("?")
    public String single() {
        return "single root level";
    }

    @GetMapping("abc/abc")
    public String aaaAbc() {
        return "aaaAbc";
    }
    @GetMapping("/**c/abc")
    public String xxAbc() {
        return "xxabc";
    }

    @GetMapping("/abc/**c")
    public String abcXX() {
        return "abcXX";
    }

    @GetMapping("a*/ab")
    public String axAb() {
        return "ax/ab";
    }

    @GetMapping("ab/a*")
    public String abAx() {
        return "ab/ax";
    }
}
