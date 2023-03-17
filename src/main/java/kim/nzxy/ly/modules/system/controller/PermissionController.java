package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kim.nzxy.ly.common.res.Res;
import kim.nzxy.ly.modules.system.dto.PermissionEditDTO;
import kim.nzxy.ly.modules.system.entity.Permission;
import kim.nzxy.ly.modules.system.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sys-permission")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "权限")
public class PermissionController {
    private final PermissionService service;

    @GetMapping("all")
    public Res<List<Permission>> all() {
        return Res.ok(service.all());
    }

    @PostMapping("edit")
    @Operation(summary = "编辑")
    public Res<Object> edit(@RequestBody PermissionEditDTO record) {
        service.edit(record);
        return Res.ok();
    }

    @DeleteMapping("remove/{id}")
    public Res<Object> delete(@PathVariable Long id) {
        service.delete(id);
        return Res.ok();
    }
}
