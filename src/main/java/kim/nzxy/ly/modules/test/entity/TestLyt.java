package kim.nzxy.ly.modules.test.entity;

import kim.nzxy.ly.common.annotation.Lyt;
import kim.nzxy.ly.common.enums.LytEnum;
import lombok.Data;

@Data
public class TestLyt {
    @Lyt(LytEnum.NICKNAME)
    private String userId;
}
