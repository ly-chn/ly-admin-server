package kim.nzxy.ly.modules.system.dto;

/**
 * 修改/新增字典信息
 */
public record DictSaveDTO(
        Long id,
        String dictName,
        String dictCode,
        String dictType,
        String remark
) {
}
