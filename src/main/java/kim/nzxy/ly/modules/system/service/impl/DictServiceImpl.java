package kim.nzxy.ly.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.util.BeanUtil;
import kim.nzxy.ly.modules.system.dto.DictSaveDTO;
import kim.nzxy.ly.modules.system.entity.Dict;
import kim.nzxy.ly.modules.system.mapper.DictMapper;
import kim.nzxy.ly.modules.system.query.DictQuery;
import kim.nzxy.ly.modules.system.service.DictService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author ly-chn
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public void edit(DictSaveDTO record) {
        this.save(BeanUtil.toBean(record, Dict.class));
    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }

    @Override
    public Object search(DictQuery query) {
        this.lambdaQuery().in(Dict::getId, Arrays.asList(1, 2)).list();
        return null;
    }
}