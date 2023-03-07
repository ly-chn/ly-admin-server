package kim.nzxy.ly.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kim.nzxy.ly.modules.system.dto.DictItemEditDTO;
import kim.nzxy.ly.modules.system.entity.DictItem;
import com.baomidou.mybatisplus.extension.service.IService;
import kim.nzxy.ly.modules.system.query.DictItemQuery;
import kim.nzxy.ly.modules.system.vo.DictItemVO;

import java.util.List;

public interface DictItemService extends IService<DictItem>{


    Page<DictItem> search(DictItemQuery query);

    void edit(DictItemEditDTO record);

    void delete(Long id);

    List<DictItemVO> getByDictCode(String dictCode);
}
