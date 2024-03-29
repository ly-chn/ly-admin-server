package kim.nzxy.ly.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import kim.nzxy.ly.modules.system.dto.DictEditDTO;
import kim.nzxy.ly.modules.system.entity.Dict;

import java.util.List;

public interface DictService extends IService<Dict> {


    void edit(DictEditDTO record);

    void delete(Long id);

    List<Dict> all();
}





