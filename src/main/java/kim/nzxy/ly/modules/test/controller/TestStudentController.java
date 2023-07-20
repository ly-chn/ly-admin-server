package kim.nzxy.ly.modules.test.controller;

import com.apifan.common.random.RandomSource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kim.nzxy.ly.common.config.excel.ExcelUtil;
import kim.nzxy.ly.common.res.Res;
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
    public Res<List<TestStudentImportDTO>> readExcel(MultipartFile file) {
        List<TestStudentImportDTO> read = ExcelUtil.read(file, TestStudentImportDTO.class);
        log.info("读取到的数据: {}", read);
        return Res.ok("成功导入" + read.size() + "条数据");
    }

    @GetMapping("export")
    public void writeExcel() {
        List<TestStudentImportDTO> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            TestStudentImportDTO item = new TestStudentImportDTO();
            item.setBirthday(RandomSource.dateTimeSource().randomLocalDate(2001));
            item.setStarCount(RandomSource.numberSource().randomInt(100, 200));
            item.setStuName(RandomSource.personInfoSource().randomChineseName());
            item.setSex(RandomSource.personInfoSource().randomGender() == 1 ? "男" : "女");
            item.setStuNum("No."+RandomSource.numberSource().randomInt(100000, 9999999));
            list.add(item);
        }
        ExcelUtil.write("导出文件", "sheet1", TestStudentImportDTO.class, list);
    }
}
