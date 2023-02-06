package kim.nzxy.ly.common.res;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import kim.nzxy.ly.common.util.RequestContextUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * 分页工具类
 *
 * @author ly-chn
 */
@Slf4j
public class Paging {
    public static <T> Page<T> startPage() {
        HttpServletRequest request = RequestContextUtil.getRequest();
        Page<T> page;
        try {
            String pageNum = request.getParameter("pageNum");
            String pageSize = request.getParameter("pageSize");
            if (pageNum == null) {
                pageNum = "1";
            }
            if (pageSize == null) {
                pageSize = "10";
            }
            page = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        } catch (Exception e) {
            log.error("获取分页信息失败, 使用默认值", e);
            page = new Page<>(1, 10);
        }
        return page;
    }
}
