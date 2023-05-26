package com.xqin.component.modules.web.core.handler;

import com.xqin.component.dto.Response;
import com.xqin.component.enums.SysExceptionEnum;
import com.xqin.component.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 全局异常处理器
 * 异常返回:Response
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有异常，主要是提供给 Filter 使用
     * 因为 Filter 不走 SpringMVC 的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保持逻辑统一。
     *
     * @param request 请求
     * @param ex 异常
     * @return Response
     */
    public Response allExceptionHandler(HttpServletRequest request, Throwable ex) {

        if (ex instanceof NoHandlerFoundException) {
            return noHandlerFoundExceptionHandler(request, (NoHandlerFoundException) ex);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return httpRequestMethodNotSupportedExceptionHandler((HttpRequestMethodNotSupportedException) ex);
        }
        if (ex instanceof HttpMediaTypeNotSupportedException) {
            return httpMediaTypeNotSupportedExceptionHandler((HttpMediaTypeNotSupportedException) ex);
        }
        if (ex instanceof MissingPathVariableException) {
            return missingPathVariableExceptionHandler((MissingPathVariableException) ex);
        }
        if (ex instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) ex);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return methodArgumentNotValidExceptionExceptionHandler((MethodArgumentNotValidException) ex);
        }
        if (ex instanceof BindException) {
            return bindExceptionHandler((BindException) ex);
        }
        if (ex instanceof ConstraintViolationException) {
            return constraintViolationExceptionHandler((ConstraintViolationException) ex);
        }
        if (ex instanceof ValidationException) {
            return validationExceptionHandler((ValidationException) ex);
        }
        if (ex instanceof BizException) {
            return bizExceptionHandler((BizException) ex);
        }
        return defaultExceptionHandler(ex);
    }

    /**
     * 处理 SpringMVC 请求地址不存在（404异常）
     *
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Response noHandlerFoundExceptionHandler(HttpServletRequest req, NoHandlerFoundException ex) {
        log.error("[noHandlerFoundExceptionHandler]", ex);
        return Response.buildFailure(SysExceptionEnum.NOT_FOUND.getCode(), String.format("请求地址不存在:%s", ex.getRequestURL()));
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     *
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.error("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return Response.buildFailure(SysExceptionEnum.METHOD_NOT_ALLOWED.getCode(), String.format("请求方法不正确:%s", ex.getMessage()));
    }

    /**
     * 处理 SpringMVC 请求头不匹配
     *
     * 例如说，A 接口的请求头 content-type 为JSON，结果请求方法为 from 表单，导致不匹配
     */
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public Response httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException ex) {
        log.error("[httpMediaTypeNotSupportedExceptionHandler]", ex);
        return Response.buildFailure(SysExceptionEnum.BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", ex.getMessage()));
    }

    /**
     * 处理 SpringMVC 路径参数缺失（/api/{xx}）
     *
     * 例如说，接口上设置了 @PathVariable("xx") 参数，路径并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingPathVariableException.class)
    public Response missingPathVariableExceptionHandler(MissingPathVariableException ex) {
        log.error("[missingPathVariableExceptionHandler]", ex);
        return Response.buildFailure(SysExceptionEnum.BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", ex.getVariableName()));
    }

    /**
     * 处理 SpringMVC 请求参数缺失
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Response missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.error("[missingServletRequestParameterExceptionHandler]", ex);
        return Response.buildFailure(SysExceptionEnum.BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", ex.getParameterName()));
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.error("[missingServletRequestParameterExceptionHandler]", ex);
        return Response.buildFailure(SysExceptionEnum.BAD_REQUEST.getCode(), String.format("请求参数类型错误:%s", ex.getMessage()));
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("[methodArgumentNotValidExceptionExceptionHandler]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null; // 断言，避免告警
        return Response.buildFailure(SysExceptionEnum.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException.class)
    public Response bindExceptionHandler(BindException ex) {
        log.error("[bindExceptionHandler]", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null; // 断言，避免告警
        return Response.buildFailure(SysExceptionEnum.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return Response.buildFailure(SysExceptionEnum.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", constraintViolation.getMessage()));
    }

    /**
     * 处理 Dubbo Consumer 本地参数校验时，抛出的 ValidationException 异常
     */
    @ExceptionHandler(value = ValidationException.class)
    public Response validationExceptionHandler(ValidationException ex) {
        log.error("[validationExceptionHandler]", ex);
        // 无法拼接明细的错误信息，因为 Dubbo Consumer 抛出 ValidationException 异常时，是直接的字符串信息，且人类不可读
        return Response.buildFailure(SysExceptionEnum.BAD_REQUEST.getCode(),"");
    }

    /**
     * 处理业务异常 BizException
     *
     * 例如说，商品库存不足，用户手机号已存在。
     */
    @ExceptionHandler(value = BizException.class)
    public Response bizExceptionHandler(BizException ex) {
        log.error("[bizExceptionHandler]", ex);
        return Response.buildFailure(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public Response defaultExceptionHandler(Throwable ex) {
        log.error("[defaultExceptionHandler]", ex);
        return Response.buildFailure(SysExceptionEnum.INTERNAL_SERVER_ERROR.getCode(), SysExceptionEnum.INTERNAL_SERVER_ERROR.getMsg());
    }

}
