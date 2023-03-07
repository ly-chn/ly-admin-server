package kim.nzxy.ly.modules.test.controller;

import kim.nzxy.ly.modules.system.service.PermissionService;
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
@RequestMapping("/sys-permission")
@RequiredArgsConstructor
@Slf4j
public class PermissionController {
    /**
    * 服务对象
    */
    private final PermissionService service;


}
