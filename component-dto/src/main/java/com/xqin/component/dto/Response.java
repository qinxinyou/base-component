package com.xqin.component.dto;

/**
 * Response to caller
 */
public class Response extends DTO {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private Integer code;

    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    @Override
    public String toString() {
        return "Response [success=" + success + ", code=" + code + ", msg=" + msg + "]";
    }

    public static Response buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    public static Response buildFailure(Integer code, String msg) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

//    public static Response buildFailure(BaseEnum baseEnum) {
//        Response response = new Response();
//        response.setSuccess(false);
//        response.setCode(code);
//        response.setMsg(msg);
//        return response;
//    }

}
