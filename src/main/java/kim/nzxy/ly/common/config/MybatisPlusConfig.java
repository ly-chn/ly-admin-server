package kim.nzxy.ly.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import kim.nzxy.ly.common.entity.BaseNormalEntity;
import kim.nzxy.ly.common.util.TokenUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * mybatis plus config
 *
 * @author xuyf
 * @see <a href="https://baomidou.com/">官方文档</a>
 * @since 2022/7/26 14:33
 */
@Configuration
@MapperScan("kim.nzxy.ly.**.mapper")
public class MybatisPlusConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, BaseNormalEntity.Fields.createBy, TokenUtil::getLoginId, Long.class);
        this.strictInsertFill(metaObject, BaseNormalEntity.Fields.createTime, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, BaseNormalEntity.Fields.updateBy, TokenUtil::getLoginId, Long.class);
        this.strictUpdateFill(metaObject, BaseNormalEntity.Fields.updateTime, LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, BaseNormalEntity.Fields.updateBy, TokenUtil::getLoginId, Long.class);
        this.strictUpdateFill(metaObject, BaseNormalEntity.Fields.updateTime, LocalDateTime::now, LocalDateTime.class);
    }

    /**
     * mybatis plus 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
