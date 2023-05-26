package com.xqin.component.dto;

/**
 * Response with single record to return
 */
public class SingleResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static SingleResponse buildSuccess() {
        SingleResponse response = new SingleResponse();
        response.setSuccess(true);
        return response;
    }

    public static <T> SingleResponse<T> buildSuccess(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static SingleResponse buildFailure(Integer code, String msg) {
        SingleResponse response = new SingleResponse();
        response.setSuccess(false);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }



}
