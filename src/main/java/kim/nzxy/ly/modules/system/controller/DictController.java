package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.tags.Tag;
import kim.nzxy.ly.modules.system.entity.Dict;
import kim.nzxy.ly.modules.system.service.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* 
*
* @author ly-chn
*/
@RestController
@RequestMapping("sys-dict")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "字典")
@SaIgnore
public class DictController {
    /**
    * 服务对象
    */
    private final DictService service;


    @GetMapping
    public Dict test() {
        return new Dict();
    }


}
