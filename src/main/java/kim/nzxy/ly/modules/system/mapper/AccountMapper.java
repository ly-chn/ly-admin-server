package kim.nzxy.ly.modules.system.mapper;

import kim.nzxy.ly.modules.system.bo.UserLoginBO;
import kim.nzxy.ly.modules.system.dto.UserLoginDTO;

import java.util.Optional;

/**
 * 账号相关
 *
 * @author xuyf
 * @since 2022/7/29 10:20
 */
public interface AccountMapper {
    /**
     * 读取用户已授权信息
     *
     * @param loginInfo 用户登录信息
     * @return 用户已授权信息
     */
    Optional<UserLoginBO> findUserAuth(UserLoginDTO loginInfo);
}
