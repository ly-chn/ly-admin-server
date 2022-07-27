package kim.nzxy.ly.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.modules.system.entity.UserAuth;
import kim.nzxy.ly.modules.system.mapper.UserAuthMapper;
import kim.nzxy.ly.modules.system.service.UserAuthService;
import org.springframework.stereotype.Service;

/**
 * @author xuyf
 * @since 2022/7/27 14:39
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

}
