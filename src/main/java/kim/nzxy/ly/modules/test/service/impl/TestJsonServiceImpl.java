package kim.nzxy.ly.modules.test.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.modules.test.entity.TestJson;
import kim.nzxy.ly.modules.test.mapper.TestJsonMapper;
import kim.nzxy.ly.modules.test.service.TestJsonService;
@Service
public class TestJsonServiceImpl extends ServiceImpl<TestJsonMapper, TestJson> implements TestJsonService{

}
