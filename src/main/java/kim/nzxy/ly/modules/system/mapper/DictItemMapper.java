package kim.nzxy.ly.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kim.nzxy.ly.modules.system.entity.DictItem;
import kim.nzxy.ly.modules.system.vo.DictItemVO;

import java.util.List;

public interface DictItemMapper extends BaseMapper<DictItem> {
    List<DictItemVO> getByDictCode(String dictCode);
}