package com.leo.util.http.common;

/**
 * http请求返回编码枚举
 *
 * @author 刘绍林
 * @create 2017-08-12 10:27
 **/
public enum HttpStatusEnum {
    //http请求成功
    OK(200,"OK"),
    //
    ACCEPTED(202, "Accepted"),
    //
    NOT_FOUND(404, "Not Found"),
    //
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private int value;
    private String reasonPhrase;

    HttpStatusEnum(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
}
