package kim.nzxy.ly.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.common.entity.BaseEntity;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.BeanUtil;
import kim.nzxy.ly.common.util.MbpUtil;
import kim.nzxy.ly.modules.system.dto.DictEditDTO;
import kim.nzxy.ly.modules.system.entity.Dict;
import kim.nzxy.ly.modules.system.mapper.DictMapper;
import kim.nzxy.ly.modules.system.service.DictItemService;
import kim.nzxy.ly.modules.system.service.DictService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author ly-chn
 */
@Service
@AllArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Override
    public void edit(DictEditDTO record) {
        boolean exists = this.lambdaQuery()
                .eq(Dict::getDictCode, record.dictCode())
                .ne(Objects.nonNull(record.id()), BaseEntity::getId, record.id())
                .exists();
        if (exists) {
            throw new LyException.Normal("字典编码已存在");
        }
        MbpUtil.edit(this, record);
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
