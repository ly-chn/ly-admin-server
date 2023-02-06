package kim.nzxy.ly.modules.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import kim.nzxy.ly.modules.test.entity.TestStudent;
import kim.nzxy.ly.modules.test.query.TestStudentQuery;

public interface TestStudentService extends IService<TestStudent> {


    Page<TestStudent> search(TestStudentQuery query);

    Page<TestStudent> anotherSearch(TestStudentQuery query);
}
