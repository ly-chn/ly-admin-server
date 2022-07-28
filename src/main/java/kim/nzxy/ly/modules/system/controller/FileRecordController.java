package kim.nzxy.ly.modules.system.controller;

import kim.nzxy.ly.common.annotation.SaSkip;
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
 * @author xuyf
 * @since 2022/7/27 12:19
 */
@RestController
@RequestMapping("file-record")
@RequiredArgsConstructor
@Slf4j
@SaSkip
public class FileRecordController {
    private final FileRecordService service;

    @PostMapping
    public Res<FileRecord> upload(MultipartFile file, Boolean block) {
        return Res.success(service.upload(file, block));
    }
}
