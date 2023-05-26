package com.xqin.component.exception;

import com.xqin.component.enums.BaseEnum;

public interface BizExceptionAssert extends Assert, BaseEnum {

    @Override
    default BaseException newException(){
        return ExceptionFactory.bizException(getCode(),getMsg());
    };

    @Override
    default BaseException newException(Integer code, String msg){
        return ExceptionFactory.bizException(code,msg);
    };
}
