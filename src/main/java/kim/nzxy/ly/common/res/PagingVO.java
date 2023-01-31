package kim.nzxy.ly.common.res;

import lombok.Data;

import java.util.List;

/**
 * 分页响应信息
 *
 * @author ly-chn
 */
@Data
public class PagingVO<T> {
    /**
     * 总记录数
     */
    private Integer total;
    /**
     * 结果集
     */
    private List<T> list;
    /**
     * 第几页
     */
    private Integer pageNum;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer pages;
}
