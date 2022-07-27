package kim.nzxy.ly.modules.system.controller;

import kim.nzxy.ly.common.annotation.SaSkip;
import kim.nzxy.ly.modules.system.enums.FilePositionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试的
 *
 * @author xuyf
 * @since 2022/7/27 15:02
 */
@RequestMapping("test")
@RestController
@RequiredArgsConstructor
@Slf4j
@SaSkip
public class TestController {
    @GetMapping("enum")
    public FilePositionEnum test(FilePositionEnum positionEnum) {
        return positionEnum;
    }
}
