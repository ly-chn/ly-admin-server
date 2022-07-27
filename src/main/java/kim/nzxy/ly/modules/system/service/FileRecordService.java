package kim.nzxy.ly.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import kim.nzxy.ly.modules.system.entity.FileRecord;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xuyf
 * @since 2022/7/27 14:39
 */
public interface FileRecordService extends IService<FileRecord> {


    void upload(MultipartFile file, Boolean block);
}


