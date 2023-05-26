package com.xqin.component.modules.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
@ConfigurationProperties(prefix = "xqin.web")
public class WebProperties {

    @NotNull(message = "APP API 不能为空")
    private Api appApi = new Api("/app-api", "**.app.controller.**");
    @NotNull(message = "WAP API 不能为空")
    private Api wapApi = new Api("/wap-api", "**.wap.controller.**");
    @NotNull(message = "WEB API 不能为空")
    private Api webApi = new Api("/web-api", "**.web.controller.**");

    @NotNull(message = "WEB UI 不能为空")
    private Ui webUi = new Ui("/ui");

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Valid
    public static class Api {

        /**
         * API 前缀，实现所有 Controller 提供的 RESTFul API 的统一前缀
         *
         *
         * 意义：通过该前缀，避免 Swagger、Actuator 意外通过 Nginx 暴露出来给外部，带来安全性问题
         *      这样，Nginx 只需要配置转发到 /api/* 的所有接口即可。
         */
        @NotEmpty(message = "API 前缀不能为空")
        private String prefix;

        /**
         * Controller 所在包的 Ant 路径规则
         *
         * 主要目的是，给该 Controller 设置指定的 {@link #prefix}
         */
        @NotEmpty(message = "Controller 所在包不能为空")
        private String controller;

    }

    @Data
    @Valid
    @AllArgsConstructor
    public static class Ui {

        /**
         * 访问地址
         */
        private String url;

    }

}
