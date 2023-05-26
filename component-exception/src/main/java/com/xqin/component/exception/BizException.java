package com.xqin.component.exception;

import com.xqin.component.enums.BizExceptionEnum;

/**
 * BizException is known Exception, no need retry
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BizException(String msg) {
        super(BizExceptionEnum.BUSINESS_ERROR.getCode(), msg);
    }

    public BizException(Integer code, String msg) {
        super(code,msg);
    }

    public BizException(String msg, Throwable e) {
        super(BizExceptionEnum.BUSINESS_ERROR.getCode(), msg, e);
    }

    public BizException(Integer code, String msg, Throwable e) {
        super(code, msg, e);
    }

}