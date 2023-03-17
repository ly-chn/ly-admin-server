package kim.nzxy.ly.modules.system.service;

import kim.nzxy.ly.common.res.PagingVO;
import kim.nzxy.ly.modules.system.dto.PermissionEditDTO;
import kim.nzxy.ly.modules.system.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import kim.nzxy.ly.modules.system.query.PermissionQuery;

import java.util.List;

public interface PermissionService extends IService<Permission>{


    List<Permission> all();

    void edit(PermissionEditDTO record);

    void delete(Long id);
}
