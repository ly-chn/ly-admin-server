package kim.nzxy.ly.common.expand;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.interfaces.Join;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.Collection;
import java.util.List;

/**
 * 增强 mybatis plus base mapper
 *
 * @author ly-chn
 */
@SuppressWarnings({"unchecked", "unused"})
public interface LyMapper<M extends BaseMapper<T>, T> extends BaseMapper<T> {
    default Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), LyMapper.class, 1);
    }

    /**
     * 批量插入
     *
     * @param list 要插入的内容
     */
    void insertBatch(Collection<T> list);

    /**
     * 一次性插入所有数据, 需自行判断数据是否合理
     * @param entityList 实体列表
     * @return 影响的行数
     */
    int insertBatchAll(Collection<T> entityList);

    /**
     * 根据 entity 条件，查询一条记录
     * <p>查询一条记录，例如 qw.last("limit 1") 限制取一条记录, 注意：多条数据会报异常</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        List<T> list = this.selectList(queryWrapper);
        if (queryWrapper instanceof Join<?>) {
            ((Join<?>) queryWrapper).last("limit 1");
        }
        // 抄自 DefaultSqlSession#selectOne
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }
    /**
     * 链式查询 lambda 式
     * <p>注意：不支持 Kotlin </p>
     *
     * @return LambdaQueryWrapper 的包装类
     */
    default LambdaQueryChainWrapper<T> lambdaQuery() {
        return ChainWrappers.lambdaQueryChain(this, currentModelClass());
    }
}
