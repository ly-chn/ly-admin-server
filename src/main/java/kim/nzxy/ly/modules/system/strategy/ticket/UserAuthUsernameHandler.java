package kim.nzxy.ly.modules.system.strategy.ticket;

import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.Argon2Util;
import kim.nzxy.ly.modules.system.bo.UserLoginBO;
import kim.nzxy.ly.modules.system.dto.UserLoginDTO;
import kim.nzxy.ly.modules.system.enums.UserAuthTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 手机号登录
 *
 * @author xuyf
 * @since 2022/7/29 10:54
 */
@Component
public class UserAuthUsernameHandler implements UserAuthHandler {
    @Override
    public void verify(UserLoginDTO userLoginDTO, UserLoginBO loginUser) {
        if (StringUtils.isEmpty(userLoginDTO.getCredential()) ||
                !Argon2Util.verify(userLoginDTO.getCredential(), loginUser.getCredential())) {
            throw new LyException.Minor("密码错误");
        }
    }

    @Override
    public void afterPropertiesSet() {
        UserAuthStrategyFactory.register(UserAuthTypeEnum.username, this);
    }
}
