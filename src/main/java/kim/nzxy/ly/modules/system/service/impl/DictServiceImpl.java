package kim.nzxy.ly.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.util.BeanUtil;
import kim.nzxy.ly.modules.system.dto.DictSaveDTO;
import kim.nzxy.ly.modules.system.entity.Dict;
import kim.nzxy.ly.modules.system.mapper.DictMapper;
import kim.nzxy.ly.modules.system.service.DictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ly-chn
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public void edit(DictSaveDTO record) {
        // todo: mbpUtil.saveOrUpdate
        this.saveOrUpdate(BeanUtil.toBean(record, Dict.class));
    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }


    @Override
    public List<Dict> all() {
        return this.list();
    }
}
