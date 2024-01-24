package kim.nzxy.ly.modules.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kim.nzxy.ly.common.res.Res;
import kim.nzxy.ly.modules.system.dto.DictItemEditDTO;
import kim.nzxy.ly.modules.system.entity.DictItem;
import kim.nzxy.ly.modules.system.query.DictItemQuery;
import kim.nzxy.ly.modules.system.service.DictItemService;
import kim.nzxy.ly.modules.system.vo.DictItemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ly-chn
 */
@RestController
@RequestMapping("sys-dict-item")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "字典项")
@SaIgnore
public class DictItemController {
    private final DictItemService service;

    @GetMapping("search")
    public Page<DictItem> search(@Valid DictItemQuery query) {
        return service.search(query);
    }

    @PostMapping("edit")
    @Operation(summary = "编辑")
    public void edit(@RequestBody DictItemEditDTO record) {
        service.edit(record);
    }

    @DeleteMapping("remove/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("code/{dictCode}")
    public List<DictItemVO> getByDictCode(@PathVariable String dictCode) {
        return service.getByDictCode(dictCode);
    }

}
