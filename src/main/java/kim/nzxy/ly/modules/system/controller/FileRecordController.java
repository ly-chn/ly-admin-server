package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import kim.nzxy.ly.common.res.Res;
import kim.nzxy.ly.modules.system.entity.FileRecord;
import kim.nzxy.ly.modules.system.service.FileRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件相关
 *
 * @author ly-chn
 */
@RestController
@RequestMapping("file-record")
@RequiredArgsConstructor
@Slf4j
public class FileRecordController {
    private final FileRecordService service;

    @PostMapping
    public FileRecord upload(MultipartFile file, Boolean block) {
        return service.upload(file, block);
    }
}
