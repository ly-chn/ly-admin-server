package kim.nzxy.ly.modules.system.strategy;

import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.Argon2Util;
import kim.nzxy.ly.modules.system.bo.UserLoginBO;
import kim.nzxy.ly.modules.system.enums.UserAuthTypeEnum;
import kim.nzxy.ly.modules.system.vo.UserLoginVO;
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
    public void verify(UserLoginVO userLoginVO, UserLoginBO loginUser) {
        if (StringUtils.isEmpty(userLoginVO.getCredential()) ||
                !Argon2Util.verify(userLoginVO.getCredential(), loginUser.getCredential())) {
            throw new LyException.Minor("密码错误");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserAuthStrategyFactory.register(UserAuthTypeEnum.username, this);
    }
}
