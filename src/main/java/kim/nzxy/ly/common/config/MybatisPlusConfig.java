package kim.nzxy.ly.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import kim.nzxy.ly.common.entity.BaseEntity;
import kim.nzxy.ly.common.entity.BaseFinalEntity;
import kim.nzxy.ly.common.util.TokenUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis plus config
 *
 * @author ly-chn
 * @see <a href="https://baomidou.com/">官方文档</a>
 */
@Component
@MapperScan("kim.nzxy.ly.**.mapper")
public class MybatisPlusConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, BaseFinalEntity.Fields.createBy, TokenUtil::getLoginId, Long.class);
        this.strictInsertFill(metaObject, BaseFinalEntity.Fields.createTime, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, BaseEntity.Fields.updateBy, TokenUtil::getLoginId, Long.class);
        this.strictInsertFill(metaObject, BaseEntity.Fields.updateTime, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, BaseEntity.Fields.deleted, () -> false, Boolean.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, BaseEntity.Fields.updateBy, TokenUtil::getLoginId, Long.class);
        this.strictUpdateFill(metaObject, BaseEntity.Fields.updateTime, LocalDateTime::now, LocalDateTime.class);
    }

    /**
     * mybatis plus 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        PaginationInnerInterceptor paging = new PaginationInnerInterceptor();
        paging.setMaxLimit(1000L);
        paging.setOverflow(true);
        interceptor.addInnerInterceptor(paging);
        return interceptor;
    }
}
