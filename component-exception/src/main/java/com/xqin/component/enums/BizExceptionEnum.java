package com.xqin.component.enums;

import com.xqin.component.exception.BizExceptionAssert;

public enum BizExceptionEnum implements BizExceptionAssert {

    BUSINESS_ERROR(1000, "业务异常");

    private Integer code;
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }
    @Override
    public String getMsg() {
        return msg;
    }

    BizExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
