package kim.nzxy.ly.modules.system.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户登录查询信息
 *
 * @author xuyf
 * @since 2022/7/29 10:14
 */
@Data
public class UserLoginBO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 授权凭证
     */
    private String credential;
    /**
     * 用户解封时间
     */
    private LocalDateTime blockTo;
}
