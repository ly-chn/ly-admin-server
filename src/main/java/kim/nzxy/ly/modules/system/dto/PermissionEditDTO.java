package kim.nzxy.ly.modules.system.dto;

public record PermissionEditDTO(
        Long id,
        Long parentId,
        String name,
        String icon,
        String path,
        String component,
        String componentName,
        String redirect,
        String permissionType,
        String authSymbol,
        Long orderWeight,
        Boolean keepAlive,
        Boolean hidden,
        Boolean disabled,
        String remark,
        Boolean createTab
) {
}
