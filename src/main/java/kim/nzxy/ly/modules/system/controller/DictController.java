package kim.nzxy.ly.modules.system.controller;

import kim.nzxy.ly.modules.system.service.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* 
*
* @author ly-chn
*/
@RestController
@RequestMapping("/sys-dict")
@RequiredArgsConstructor
@Slf4j
public class DictController {
    /**
    * 服务对象
    */
    private final DictService service;



}
