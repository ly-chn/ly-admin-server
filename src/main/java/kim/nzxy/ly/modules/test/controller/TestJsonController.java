package kim.nzxy.ly.modules.test.controller;

import kim.nzxy.ly.common.res.Res;
import kim.nzxy.ly.modules.test.entity.TestJson;
import kim.nzxy.ly.modules.test.service.TestJsonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author ly-chn
 */
@RestController
@RequestMapping("/test-json")
@RequiredArgsConstructor
@Slf4j
public class TestJsonController {
    /**
     * 服务对象
     */
    private final TestJsonService service;


    @PostMapping
    public void insert(@RequestBody TestJson record) {
        service.save(record);
    }

    @GetMapping
    public List<TestJson> get() {
        return service.list();
    }
}
