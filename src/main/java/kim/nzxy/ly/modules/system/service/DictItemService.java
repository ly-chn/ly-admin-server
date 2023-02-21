package kim.nzxy.ly.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kim.nzxy.ly.modules.system.dto.DictSaveDTO;
import kim.nzxy.ly.modules.system.entity.DictItem;
import com.baomidou.mybatisplus.extension.service.IService;
import kim.nzxy.ly.modules.system.query.DictItemQuery;

public interface DictItemService extends IService<DictItem>{


    Page<DictItem> search(DictItemQuery query);

    void edit(DictSaveDTO record);

    void delete(Long id);
}
