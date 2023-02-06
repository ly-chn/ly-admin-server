package kim.nzxy.ly.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kim.nzxy.ly.modules.system.entity.User;
import kim.nzxy.ly.modules.system.mapper.UserMapper;
import kim.nzxy.ly.modules.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ly-chn
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
