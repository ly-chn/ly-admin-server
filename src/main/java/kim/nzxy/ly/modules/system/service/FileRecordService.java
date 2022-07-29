package kim.nzxy.ly.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import kim.nzxy.ly.modules.system.entity.FileRecord;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xuyf
 * @since 2022/7/27 14:39
 */
public interface FileRecordService extends IService<FileRecord> {


    /**
     * 上传文件
     *
     * @param file  文件
     * @param block 是否锁定, 锁定后需要授权才能读取文件
     * @return 文件记录
     */
    FileRecord upload(MultipartFile file, Boolean block);
}



