package kim.nzxy.ly.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import kim.nzxy.ly.modules.system.dto.DictSaveDTO;
import kim.nzxy.ly.modules.system.entity.Dict;
import kim.nzxy.ly.modules.system.query.DictQuery;

public interface DictService extends IService<Dict> {


    void edit(DictSaveDTO record);

    void delete(Long id);

    Page<Dict> search(DictQuery query);
}





