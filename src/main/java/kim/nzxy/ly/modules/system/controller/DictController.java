package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kim.nzxy.ly.common.res.Res;
import kim.nzxy.ly.modules.system.dto.DictSaveDTO;
import kim.nzxy.ly.modules.system.entity.Dict;
import kim.nzxy.ly.modules.system.query.DictQuery;
import kim.nzxy.ly.modules.system.service.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author ly-chn
 */
@RestController
@RequestMapping("sys-dict")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "字典")
@SaIgnore
public class DictController {

    private final DictService service;

    @GetMapping
    @Operation(summary = "列表查询")
    public Res<Object> search(DictQuery query) {
        return Res.ok(service.search(query));
    }

    @PostMapping
    @Operation(summary = "编辑")
    public Res<Object> edit(@RequestBody DictSaveDTO record) {
        service.edit(record);
        return Res.ok();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
