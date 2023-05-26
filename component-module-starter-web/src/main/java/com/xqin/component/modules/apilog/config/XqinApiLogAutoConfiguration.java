package com.xqin.component.modules.apilog.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;

@AutoConfiguration(after = com.xqin.component.modules.web.config.XqinWebAutoConfiguration.class)
public class XqinApiLogAutoConfiguration {
    //todo 打印请求api接口信息
}
