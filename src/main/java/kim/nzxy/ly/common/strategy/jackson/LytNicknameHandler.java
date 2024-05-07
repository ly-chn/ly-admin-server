package kim.nzxy.ly.common.strategy.jackson;

import kim.nzxy.ly.common.enums.LytEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class LytNicknameHandler implements LytHandler<Serializable, String>, InitializingBean {
    @Override
    public String t(Serializable key) {
        return "some nickname";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LytStrategyFactory.register(LytEnum.NICKNAME, this);
    }
}
