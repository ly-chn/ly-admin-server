package kim.nzxy.ly.modules.test.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.res.Paging;
import kim.nzxy.ly.common.util.MbpUtil;
import kim.nzxy.ly.modules.test.entity.TestStudent;
import kim.nzxy.ly.modules.test.mapper.TestStudentMapper;
import kim.nzxy.ly.modules.test.query.TestStudentQuery;
import kim.nzxy.ly.modules.test.service.TestStudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class TestStudentServiceImpl extends ServiceImpl<TestStudentMapper, TestStudent> implements TestStudentService {

    @Override
    public Page<TestStudent> search(TestStudentQuery query) {
        return this.lambdaQuery()
                // 学号不为空, 则模糊查询
                .like(StringUtils.isNotEmpty(query.stuNum()), TestStudent::getStuNum, query.stuNum())
                // 性别不为空则全等查询
                .eq(StringUtils.isNotEmpty(query.sex()), TestStudent::getSex, query.sex())
                // 生日最小/最大时间均不为空则进行范围查询
                .between(Objects.nonNull(query.birthdayMax()) && Objects.nonNull(query.birthdayMin()),
                        TestStudent::getBirthday, query.birthdayMin(), query.birthdayMax())
                // 受表扬次数不为空则全等查询
                .eq(Objects.nonNull(query.starCount()), TestStudent::getStarCount, query.starCount())
                // 分页
                .page(Paging.startPage());
    }

    @Override
    public Page<TestStudent> anotherSearch(TestStudentQuery query) {
        return MbpUtil.page(this, query);
    }
}
