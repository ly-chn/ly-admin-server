package kim.nzxy.ly.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kim.nzxy.ly.common.util.BeanUtil;
import kim.nzxy.ly.common.util.MbpUtil;
import kim.nzxy.ly.modules.system.dto.DictSaveDTO;
import kim.nzxy.ly.modules.system.query.DictItemQuery;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.modules.system.entity.DictItem;
import kim.nzxy.ly.modules.system.mapper.DictItemMapper;
import kim.nzxy.ly.modules.system.service.DictItemService;
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService{

    @Override
    public Page<DictItem> search(DictItemQuery query) {
        return MbpUtil.page(this, query);
    }

    @Override
    public void edit(DictSaveDTO record) {
        this.saveOrUpdate(BeanUtil.toBean(record, DictItem.class));
    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }
}
