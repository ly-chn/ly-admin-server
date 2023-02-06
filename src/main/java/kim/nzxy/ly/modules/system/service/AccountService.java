package kim.nzxy.ly.modules.system.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import kim.nzxy.ly.modules.system.dto.UserLoginDTO;

/**
 * 账户相关
 *
 * @author ly-chn
 */
public interface AccountService {
    void register(String username, String password);

    SaTokenInfo login(UserLoginDTO loginInfo);
}
