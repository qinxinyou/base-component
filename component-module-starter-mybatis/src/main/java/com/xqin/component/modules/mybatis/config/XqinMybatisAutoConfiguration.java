package com.xqin.component.modules.mybatis.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.xqin.component.modules.mybatis.core.handler.DefaultDBFieldHandler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBaits 配置类:
 * 1 设置mapper扫描基础包
 * 2 启动事务管理
 * 3 注册分页插件
 * 4 自动填充参数
 */
@AutoConfiguration
@MapperScan(value = "${mybatis.mapper-package}")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties(MybatisPlusProperties.class)
public class XqinMybatisAutoConfiguration {

    /**
     * 注册插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
//        //数据库类型(根据类型获取应使用的分页方言)
//        paginationInnerInterceptor.setDbType();
//        //溢出总页数后是否进行处理(默认不处理),默认值：false
//        paginationInnerInterceptor.setOverflow();
//        //单页分页条数限制(默认无限制)
//        paginationInnerInterceptor.setMaxLimit();
//        //方言实现类
//        paginationInnerInterceptor.setDialect();
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mybatisPlusInterceptor;
    }

    /**
     * 自动填充参数类
     */
    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(){
        return new DefaultDBFieldHandler();
    }

//    @Bean
//    @ConditionalOnProperty(prefix = "mybatis-plus.global-config.db-config", name = "id-type", havingValue = "INPUT")
//    public IKeyGenerator keyGenerator(ConfigurableEnvironment environment) {
//        DbType dbType = IdTypeEnvironmentPostProcessor.getDbType(environment);
//        if (dbType != null) {
//            switch (dbType) {
//                case POSTGRE_SQL:
//                    return new PostgreKeyGenerator();
//                case ORACLE:
//                case ORACLE_12C:
//                    return new OracleKeyGenerator();
//                case H2:
//                    return new H2KeyGenerator();
//                case KINGBASE_ES:
//                    return new KingbaseKeyGenerator();
//            }
//        }
//        // 找不到合适的 IKeyGenerator 实现类
//        throw new IllegalArgumentException(StrUtil.format("DbType{} 找不到合适的 IKeyGenerator 实现类", dbType));
//    }

}
