package kim.nzxy.ly.modules.test.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import kim.nzxy.ly.common.config.excel.ExcelContextUtil;
import kim.nzxy.ly.common.config.excel.ExcelUtil;
import kim.nzxy.ly.common.util.RequestContextUtil;
import kim.nzxy.ly.modules.test.dto.TestStudentImportDTO;
import kim.nzxy.ly.modules.test.entity.TestStudent;
import kim.nzxy.ly.modules.test.query.TestStudentQuery;
import kim.nzxy.ly.modules.test.service.TestStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author ly-chn
 */
@RestController
@RequestMapping("/test-student")
@RequiredArgsConstructor
@Slf4j
public class TestStudentController {
    /**
     * 服务对象
     */
    private final TestStudentService service;


    @GetMapping("search")
    public Page<TestStudent> search(TestStudentQuery query) {
        return service.anotherSearch(query);
    }

    @PostMapping("import")
    public void readExcel(MultipartFile file) {
        List<TestStudentImportDTO> read = ExcelUtil.read(file, TestStudentImportDTO.class);
        log.info("已导出: {}", read);
    }
}
