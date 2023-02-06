package kim.nzxy.ly.modules.system.dto;

import kim.nzxy.ly.modules.system.enums.UserAuthTypeEnum;
import lombok.Data;

/**
 * 用户登录
 *
 * @author ly-chn
 */
@Data
public class UserLoginDTO {
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
