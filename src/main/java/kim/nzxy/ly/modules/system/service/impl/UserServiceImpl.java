package kim.nzxy.ly.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.modules.system.entity.User;
import kim.nzxy.ly.modules.system.mapper.UserMapper;
import kim.nzxy.ly.modules.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xuyf
 * @since 2022/7/27 14:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
