package com.xqin.component.exception;

import com.xqin.component.enums.SysExceptionEnum;

/**
 * System Exception is unexpected Exception, retry might work again
 */
public class SysException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SysException(String msg) {
        super(SysExceptionEnum.UNKNOWN.getCode(), msg);
    }

    public SysException(Integer code, String msg) {
        super(code, msg);
    }

    public SysException(String msg, Throwable e) {
        super(SysExceptionEnum.UNKNOWN.getCode(), msg, e);
    }

    public SysException(Integer code, String msg, Throwable e) {
        super(code, msg, e);
    }

}
