package com.payencai.library.http.task;


import com.payencai.library.util.LogUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by payencai on 2018/5/20.
 */

public class RequestTask implements Runnable {
    private Request request;
    private HttpListener httpListener;
    public Request getRequest() {
        return request;
    }

    public RequestTask(Request request,HttpListener httpListener) {
        this.request = request;
        this.httpListener=httpListener;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        LogUtil.i("TAG",request.toString());
        Response response=null;
        Exception exception=null;
        Map<String,List<String>> resHeader=null;
        int resCode=-1;
        String urlStr=request.getReqUrl();
        RequestMethod method=request.getReqMethod();
        if(method==RequestMethod.GET) {

        }else if(method==RequestMethod.POST){

        }


        Message message=new Message(response,httpListener);
        PosterHandler.getInstance().post(message);

    }
}
