package kim.nzxy.ly.modules.system.query;

import kim.nzxy.ly.common.annotation.MbpQuery;
import kim.nzxy.ly.common.enums.QueryTypeEnum;

/**
 * 字典检索
 *
 * @param dictName 字典名称
 * @param dictCode 字典编码
 * @param dictType 字典类型
 */
public record DictQuery(
        @MbpQuery(type = QueryTypeEnum.LIKE)
        String dictName,
        @MbpQuery(type = QueryTypeEnum.LIKE)
        String dictCode,
        @MbpQuery(type = QueryTypeEnum.EQ)
        String dictType
) {
}
