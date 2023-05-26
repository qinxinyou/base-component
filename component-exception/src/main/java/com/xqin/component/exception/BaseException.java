package com.xqin.component.exception;

/**
 * Base Exception is the parent of all exceptions
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BaseException(String msg, Throwable e) {
        super(msg, e);
    }

    public BaseException(Integer code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

}
