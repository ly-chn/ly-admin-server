package kim.nzxy.ly.modules.system.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import kim.nzxy.ly.common.exception.LyException;
import kim.nzxy.ly.common.util.TokenUtil;
import kim.nzxy.ly.modules.system.bo.UserLoginBO;
import kim.nzxy.ly.modules.system.dto.UserLoginDTO;
import kim.nzxy.ly.modules.system.mapper.AccountMapper;
import kim.nzxy.ly.modules.system.service.AccountService;
import kim.nzxy.ly.modules.system.strategy.ticket.UserAuthStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author ly-chn
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountMapper mapper;

    @Override
    public SaTokenInfo login(UserLoginDTO loginInfo) {
        Optional<UserLoginBO> loginBO = mapper.findUserAuth(loginInfo);
        loginBO.orElseThrow(() -> new LyException.Minor("用户信息不存在"));
        UserLoginBO loginUser = loginBO.get();
        if (loginUser.getBlockTo().isAfter(LocalDateTime.now())) {
            throw new LyException.Normal("用户已锁定");
        }
        // 校验授权信息是否正确
        UserAuthStrategyFactory.getStrategy(loginInfo.getAuthType()).verify(loginInfo, loginUser);
        return TokenUtil.login(loginUser.getUserId());
    }
}
