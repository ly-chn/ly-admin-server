package kim.nzxy.ly.common.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import kim.nzxy.ly.common.config.method.InsertList;
import kim.nzxy.ly.common.entity.BaseEntity;
import kim.nzxy.ly.common.entity.BaseFinalEntity;
import kim.nzxy.ly.common.util.TokenUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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

    @Bean
    public DefaultSqlInjector sqlInjector() {
        return new DefaultSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
                // 批量插入
                methodList.add(new InsertList());
                return methodList;
            }
        };
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer(ObjectMapper objectMapper) {
        // 覆盖默认om
        JacksonTypeHandler.setObjectMapper(objectMapper);
        return configuration -> configuration.getTypeHandlerRegistry()
                .register(JacksonTypeHandler.class);
    }
}
