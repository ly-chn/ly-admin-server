package kim.nzxy.ly.modules.system.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.properties.AppProperties;
import kim.nzxy.ly.modules.system.entity.FileRecord;
import kim.nzxy.ly.modules.system.enums.FilePositionEnum;
import kim.nzxy.ly.modules.system.mapper.FileRecordMapper;
import kim.nzxy.ly.modules.system.service.FileRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author xuyf
 * @since 2022/7/27 14:39
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileRecordServiceImpl extends ServiceImpl<FileRecordMapper, FileRecord> implements FileRecordService {
    private final AppProperties appProperties;

    @Override
    public FileRecord upload(MultipartFile file, Boolean block) {
        String filename = file.getOriginalFilename();
        String ext = FileNameUtil.extName(filename);
        assert ext != null : "无法获取文件信息";
        FileRecord fileRecord;
        try {
            String md5 = SecureUtil.md5(file.getInputStream());
            Optional<FileRecord> existedRecordOpt = this.lambdaQuery()
                    .eq(FileRecord::getHash, md5)
                    .oneOpt();
            if (existedRecordOpt.isPresent()) {
                log.info("文件[{}]已存在，不需要上传", filename);
                FileRecord existedRecord = existedRecordOpt.get();
                fileRecord = FileRecord.builder()
                        .hash(md5)
                        .block(block)
                        .filename(filename)
                        .ext(ext)
                        .position(existedRecord.getPosition())
                        .locator(existedRecord.getLocator())
                        .build();
                this.save(fileRecord);
                return fileRecord;
            }
            Path path = Path.of(appProperties.getFile().getUploadPath(), ext, md5 + CharPool.DOT + ext);
            // noinspection ResultOfMethodCallIgnored
            path.getParent().toFile().mkdirs();
            file.transferTo(path);
            fileRecord = FileRecord.builder().hash(md5)
                    .block(block)
                    .filename(filename)
                    .ext(ext)
                    .locator(Path.of(ext, md5 + CharPool.DOT + ext).toString())
                    .position(FilePositionEnum.local)
                    .build();
        } catch (IOException e) {
            throw new LyException.Minor("文件上传失败", e);
        }
        this.save(fileRecord);
        return fileRecord;
    }
}
