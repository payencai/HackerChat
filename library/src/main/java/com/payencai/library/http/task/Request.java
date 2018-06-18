package com.payencai.library.http.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by payencai on 2018/5/20.
 */

public class Request {
    private String reqUrl;
    private RequestMethod reqMethod;
    private List<KeyValue> keyValues;

    public Request(String url) {
        this(url,RequestMethod.GET);
    }

    public Request(String url, RequestMethod method) {
        this.reqUrl = url;
        this.reqMethod = method;
        keyValues=new ArrayList<>();
    }
    public void add(String key,String value){
        keyValues.add(new KeyValue(key,value));
    }
    public void add(String key,File value){
        keyValues.add(new KeyValue(key,value));
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public RequestMethod getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(RequestMethod reqMethod) {
        this.reqMethod = reqMethod;
    }

    public List<KeyValue> getKeyValues() {
        return keyValues;
    }

    public void setKeyValues(List<KeyValue> keyValues) {
        this.keyValues = keyValues;
    }
}
