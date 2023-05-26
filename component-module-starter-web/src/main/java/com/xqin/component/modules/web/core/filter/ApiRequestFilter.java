package com.xqin.component.modules.web.core.filter;

import cn.hutool.core.util.StrUtil;
import com.xqin.component.modules.web.config.WebProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * 过滤 /web-api 等 API 请求的过滤器
 *
 */
@RequiredArgsConstructor
public abstract class ApiRequestFilter extends OncePerRequestFilter {

    protected final WebProperties webProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 只过滤 API 请求的地址
        return !StrUtil.startWithAny(request.getRequestURI(), webProperties.getWebApi().getPrefix(),
                webProperties.getAppApi().getPrefix());
    }

}