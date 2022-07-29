package kim.nzxy.ly.modules.system.strategy;

import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.modules.system.bo.UserLoginBO;
import kim.nzxy.ly.modules.system.vo.UserLoginVO;
import org.springframework.beans.factory.InitializingBean;

/**
 * 用户授权处理
 *
 * @author xuyf
 * @since 2022/7/29 10:37
 */
public interface UserAuthHandler extends InitializingBean {
    /**
     * 用户登录
     *
     * @param userLoginVO 用户前端数据
     * @param loginUser   待登录的用户信息(来自数据库)
     */
    default void verify(UserLoginVO userLoginVO, UserLoginBO loginUser){
        throw new LyException.Normal("登录方式未实现");
    }
}
