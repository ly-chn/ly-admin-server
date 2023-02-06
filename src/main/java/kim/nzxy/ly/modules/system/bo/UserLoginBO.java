package kim.nzxy.ly.modules.system.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户登录查询信息
 *
 * @author ly-chn
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
