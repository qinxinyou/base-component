package com.xqin.component.modules.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 数据库配置类:
 * 1 启用drul连接池
 */
@AutoConfiguration
@EnableConfigurationProperties(DruidStatProperties.class)
public class XqinDataSourceAutoConfiguration {
}
