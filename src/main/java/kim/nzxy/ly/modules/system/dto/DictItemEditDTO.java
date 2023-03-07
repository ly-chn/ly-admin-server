package kim.nzxy.ly.modules.system.dto;

public record DictItemEditDTO(
        Long id,
        Long dictId,
        String label,
        Long orderWeight,
        String remark,
        String tagType,
        String tips,
        String value
) {
}
