package kim.nzxy.ly.modules.system.controller;

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
 * @author xuyf
 * @since 2022/7/27 12:19
 */
@RestController
@RequestMapping("file-record")
@RequiredArgsConstructor
@Slf4j
public class FileRecordController {
    private final FileRecordService service;

    @PostMapping
    public void upload(MultipartFile file, Boolean block) {
        service.upload(file, block);
    }
}
