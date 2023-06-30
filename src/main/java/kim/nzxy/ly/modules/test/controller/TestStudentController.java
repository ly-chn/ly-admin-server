package kim.nzxy.ly.modules.test.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import kim.nzxy.ly.common.config.excel.ExcelContextUtil;
import kim.nzxy.ly.common.config.excel.ExcelUtil;
import kim.nzxy.ly.modules.test.dto.TestStudentImportDTO;
import kim.nzxy.ly.modules.test.entity.TestStudent;
import kim.nzxy.ly.modules.test.query.TestStudentQuery;
import kim.nzxy.ly.modules.test.service.TestStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public void readExcel(MultipartFile file, HttpServletResponse response) throws IOException {
        ExcelUtil.read(file, TestStudentImportDTO.class);
    }
    @GetMapping("test")
    public void test(HttpServletResponse response) throws IOException {
        ExcelContextUtil.setDownloadHeader(response, "文件导入失败.xlsx");
        EasyExcel.write(response.getOutputStream())
                .withTemplate("C:\\Users\\Liaoliao\\Downloads\\test-import.xlsx")
                .sheet()
                .doFill(new ArrayList<>());
    }

    @GetMapping("write")
    public void writeExcel() {
        ExcelUtil.write("template", "sheet1", TestStudentImportDTO.class, Collections.emptyList());
    }

}
