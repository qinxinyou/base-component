package com.xqin.component.modules.web.core.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqin.component.dto.Response;
import com.xqin.component.dto.SingleResponse;
import com.xqin.component.modules.web.core.annotate.NotResponseAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 描述：统一返回处理
 * 如果引入了swagger或knife4j的文档生成组件，这里需要仅扫描自己项目的包，否则文档无法正常生成。
 * basePackages：扫描控制器包路径。
 */
@ControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    /**
     * 描述：response是BaseResultVO类型不需要包装（或者特定注解接口不需要包装）
     * 返回true-结果需要包装
     * 返回false-结果不需要包装
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if (methodParameter.getMethod() == null) {
            return false;
        }
        return (!methodParameter.getParameterType().isAssignableFrom(Response.class)) || methodParameter.hasMethodAnnotation(NotResponseAdvice.class);
    }

    /**
     * 描述：结果包装
     * 结果：
     * 结果是Response类型，不用包装，直接返回。
     * String类型不能直接包装。特殊处理一下。
     * todo 需要返回接口的接口，返回null情况处理，以及返回带有统一返回类型的格式的返回（例如其他系统的BaseResult）
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof Response){
            return body;
        }
        if (body instanceof String) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(SingleResponse.buildSuccess(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return SingleResponse.buildSuccess(body);
    }

}
