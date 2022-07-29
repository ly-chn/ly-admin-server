package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import kim.nzxy.ly.common.annotation.SaSkip;
import kim.nzxy.ly.common.res.Res;
import kim.nzxy.ly.common.util.TokenUtil;
import kim.nzxy.ly.modules.system.service.AccountService;
import kim.nzxy.ly.modules.system.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 账户相关/登录注册...
 *
 * @author xuyf
 * @since 2022/7/27 12:19
 */
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
@SaSkip
public class AccountController {
    private final AccountService service;

    @PostMapping("register")
    public void register(String username, String password) {
        service.register(username, password);
    }

    @PostMapping("login")
    public Res<SaTokenInfo> login(@RequestBody UserLoginVO loginInfo) {
        return Res.success(service.login(loginInfo));
    }
    @DeleteMapping("logout")
    public Res<?> logout() {
        TokenUtil.logout();
        return Res.success();
    }
}
