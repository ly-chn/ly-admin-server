package kim.nzxy.ly.modules.system.query;

/**
 * 字典检索
 * @param dictName 字典名称
 * @param dictCode 字典编码
 * @param dictType 字典类型
 */
public record DictQuery(
        String dictName,
        String dictCode,
        String dictType
) {
}
