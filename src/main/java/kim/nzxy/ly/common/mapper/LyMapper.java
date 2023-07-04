package kim.nzxy.ly.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 自定义基础mapper
 *
 * @param <T> 实体类
 * @author ly-chn
 */
public interface LyMapper<M, T> extends BaseMapper<T> {
    /**
     * 批插, 但是不自动分批
     *
     * @param entityList 要插入的数据
     */
    void insertList(Collection<T> entityList);

    /**
     * 批量插入, 分批, 每次1000条
     *
     * @param entityList 要插入的实体类列表
     */
    default void insertBatch(Collection<T> entityList) {
        insertBatch(entityList, 1000);
    }

    /**
     * 批量插入(包含限制条数)
     *
     * @param entityList 要插入的数据
     * @param batchSize  每次插入条数
     */
    default void insertBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }
        int size = entityList.size();
        int idxLimit = Math.min(batchSize, size);
        int i = 1;
        // 保存单批提交的数据集合
        List<T> oneBatchList = new ArrayList<>();
        for (Iterator<T> it = entityList.iterator(); it.hasNext(); ++i) {
            T element = it.next();
            oneBatchList.add(element);
            if (i == idxLimit) {
                insertList(oneBatchList);
                // 每次提交后需要清空集合数据
                oneBatchList.clear();
                idxLimit = Math.min(idxLimit + batchSize, size);
            }
        }
    }
}
