package com.user.response;

public class BaseResponse {

    private String message;
    private int code;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseResponse() {
    }

    public BaseResponse(String message,Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
