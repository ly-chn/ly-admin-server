package kim.nzxy.ly.modules.system.service.impl;

import kim.nzxy.ly.common.util.MbpUtil;
import kim.nzxy.ly.common.util.TreeUtil;
import kim.nzxy.ly.modules.system.dto.PermissionEditDTO;
import kim.nzxy.ly.modules.system.query.PermissionQuery;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.modules.system.entity.Permission;
import kim.nzxy.ly.modules.system.mapper.PermissionMapper;
import kim.nzxy.ly.modules.system.service.PermissionService;

import java.util.List;
import java.util.Objects;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

    @Override
    public List<Permission> all() {
        return TreeUtil.build(this.list());
    }

    @Override
    public void edit(PermissionEditDTO record) {
        MbpUtil.edit(this, record);
    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }
}
