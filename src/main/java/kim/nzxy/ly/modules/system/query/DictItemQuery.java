package kim.nzxy.ly.modules.system.query;

import jakarta.validation.constraints.NotNull;
import kim.nzxy.ly.common.annotation.MbpQuery;
import kim.nzxy.ly.common.enums.QueryTypeEnum;

public record DictItemQuery(
        @MbpQuery(type = QueryTypeEnum.EQ)
        @NotNull(message = "检索条件不合法")
        String dictId,
        @MbpQuery(type = QueryTypeEnum.LIKE)
        String label,
        @MbpQuery(type = QueryTypeEnum.LIKE)
        String value
) {
}
