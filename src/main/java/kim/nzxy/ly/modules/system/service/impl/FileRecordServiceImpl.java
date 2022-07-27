package kim.nzxy.ly.modules.system.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.properties.AppProperties;
import kim.nzxy.ly.modules.system.entity.FileRecord;
import kim.nzxy.ly.modules.system.enums.FilePositionEnum;
import kim.nzxy.ly.modules.system.mapper.FileRecordMapper;
import kim.nzxy.ly.modules.system.service.FileRecordService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

/**
 * @author xuyf
 * @since 2022/7/27 14:39
 */
@Service
@RequiredArgsConstructor
public class FileRecordServiceImpl extends ServiceImpl<FileRecordMapper, FileRecord> implements FileRecordService {
    private final AppProperties appProperties;

    @SneakyThrows(value = {NoSuchAlgorithmException.class, IOException.class})
    @Override
    public void upload(MultipartFile file, Boolean block) {
        String filename = file.getOriginalFilename();
        String ext = FileNameUtil.extName(filename);
        String md5 = SecureUtil.md5(file.getInputStream());
        assert ext != null : "无法获取文件信息";
        FileRecord fileRecord = new FileRecord()
                .setHash(md5)
                .setBlock(block)
                .setFilename(filename)
                .setExt(ext)
                .setLocator(Path.of(ext, md5 + CharPool.DOT + ext).toString())
                .setPosition(FilePositionEnum.local);
        file.transferTo(Path.of(appProperties.getFile().getUploadPath(), ext, md5 + CharPool.DOT + ext));
        this.save(fileRecord);
    }
}


