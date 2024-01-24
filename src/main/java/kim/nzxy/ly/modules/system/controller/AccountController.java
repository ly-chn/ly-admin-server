package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import jakarta.servlet.http.HttpServletRequest;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.res.Res;
import kim.nzxy.ly.common.util.TokenUtil;
import kim.nzxy.ly.modules.system.dto.UserLoginDTO;
import kim.nzxy.ly.modules.system.entity.DictItem;
import kim.nzxy.ly.modules.system.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 账户相关/登录注册...
 *
 * @author ly-chn
 */
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
@SaIgnore
public class AccountController {
    private final AccountService service;

    @PostMapping("register")
    public void register(String username, String password) {
        throw new LyException.Panic("未实现");
    }

    @PostMapping("login")
    public SaTokenInfo login(@RequestBody UserLoginDTO loginInfo) {
        return service.login(loginInfo);
    }

    @DeleteMapping("logout")
    public void logout() {
        TokenUtil.logout();
    }
}
