package kim.nzxy.ly.modules.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kim.nzxy.ly.modules.test.entity.TestStudent;
import kim.nzxy.ly.modules.test.query.TestStudentQuery;
import kim.nzxy.ly.modules.test.service.TestStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
}
