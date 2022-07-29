package kim.nzxy.ly.modules.system.vo;

import kim.nzxy.ly.modules.system.enums.UserAuthTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户登录
 *
 * @author xuyf
 * @since 2022/7/29 10:07
 */
@Data
public class UserLoginVO {
    /**
     * 授权类型
     */
    private UserAuthTypeEnum authType;

    /**
     * 授权标志, 账号, 手机号码, 邮箱地址, openid...
     */
    private String symbol;

    /**
     * 授权凭证
     */
    private String credential;
}
