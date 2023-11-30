package kim.nzxy.ly.modules.test.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.modules.test.entity.TestAny;
import kim.nzxy.ly.modules.test.mapper.TestAnyMapper;
import kim.nzxy.ly.modules.test.service.TestAnyService;
@Service
public class TestAnyServiceImpl extends ServiceImpl<TestAnyMapper, TestAny> implements TestAnyService{

}
