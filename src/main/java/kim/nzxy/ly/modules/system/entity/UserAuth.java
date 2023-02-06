package kim.nzxy.ly.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户授权信息
 *
 * @author ly-chn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_auth")
public class UserAuth {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 对应用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 授权类型, 账号, 手机号, 微信, 邮箱, 支付宝...
     */
    @TableField(value = "auth_type")
    private Integer authType;

    /**
     * 授权标志, 账号, 手机号码, 邮箱地址, openid...
     */
    @TableField(value = "symbol")
    private String symbol;

    /**
     * 授权凭证
     */
    @TableField(value = "credential")
    private String credential;
}