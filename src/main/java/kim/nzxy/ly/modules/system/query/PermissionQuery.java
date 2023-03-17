package kim.nzxy.ly.modules.system.query;

import kim.nzxy.ly.common.annotation.MbpQuery;
import kim.nzxy.ly.common.enums.QueryTypeEnum;

public record PermissionQuery (
        @MbpQuery(type = QueryTypeEnum.LIKE)
        String name
){
}
