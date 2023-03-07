package kim.nzxy.ly.modules.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.modules.system.entity.Permission;
import kim.nzxy.ly.modules.system.mapper.PermissionMapper;
import kim.nzxy.ly.modules.system.service.PermissionService;
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

}
