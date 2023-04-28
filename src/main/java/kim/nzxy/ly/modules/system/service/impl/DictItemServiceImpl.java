package kim.nzxy.ly.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.entity.BaseEntity;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.MbpUtil;
import kim.nzxy.ly.modules.system.dto.DictItemEditDTO;
import kim.nzxy.ly.modules.system.entity.DictItem;
import kim.nzxy.ly.modules.system.mapper.DictItemMapper;
import kim.nzxy.ly.modules.system.query.DictItemQuery;
import kim.nzxy.ly.modules.system.service.DictItemService;
import kim.nzxy.ly.modules.system.vo.DictItemVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {

    @Override
    public Page<DictItem> search(DictItemQuery query) {
        return MbpUtil.page(this, query);
    }

    @Override
    public void edit(DictItemEditDTO record) {
        boolean exists = this.lambdaQuery()
                .eq(DictItem::getValue, record.value())
                .eq(DictItem::getDictId, record.dictId())
                .ne(Objects.nonNull(record.id()), BaseEntity::getId, record.id())
                .exists();
        if (exists) {
            throw new LyException.Normal("该字典项的值已存在");
        }
        MbpUtil.edit(this, record);
    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }

    @Override
    public List<DictItemVO> getByDictCode(String dictCode) {
        return baseMapper.getByDictCode(dictCode);
    }
}
