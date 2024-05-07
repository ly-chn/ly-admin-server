package kim.nzxy.ly.modules.test.controller;

import kim.nzxy.ly.modules.test.entity.TestLyt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test/lyt")
public class TestLytController {
    @GetMapping("nickname")
    public TestLyt any() {
        TestLyt res = new TestLyt();
        res.setUserId("1");
        return res;
    }
}
