package kim.nzxy.ly.modules.system.strategy.ticket;

import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.modules.system.bo.UserLoginBO;
import kim.nzxy.ly.modules.system.dto.UserLoginDTO;
import org.springframework.beans.factory.InitializingBean;

/**
 * 用户授权处理
 *
 * @author ly-chn
 */
public interface UserAuthHandler extends InitializingBean {
    /**
     * 用户登录
     *
     * @param userLoginDTO 用户前端数据
     * @param loginUser    待登录的用户信息(来自数据库)
     */
    default void verify(UserLoginDTO userLoginDTO, UserLoginBO loginUser) {
        throw new LyException.Normal("登录方式未实现");
    }
}
