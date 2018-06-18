package com.payencai.library.http.task;

/**
 * Created by payencai on 2018/5/20.
 */
public enum RequestMethod {
    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    DELETE("DELETE");
    private String value;
    RequestMethod(String value) {
        this.value=value;
    }
    public String getValue(){
        return value;
    }
    @Override
    public String toString() {
        return value;
    }
}
