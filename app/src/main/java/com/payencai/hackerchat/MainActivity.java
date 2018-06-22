package com.payencai.hackerchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.payencai.library.http.task.HttpCallback;
import com.payencai.library.http.task.HttpTask;
import com.payencai.library.http.task.KeyValue;
import com.payencai.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url="http://www.weather.com.cn/data/sk/101110101.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtil.showToast(this,"hello world");
        doHttpRequest();
    }
    private void doHttpRequest() {
        List<KeyValue> keyValues=new ArrayList<>();
        Object header=null;
        HttpTask.getInstance().doget(url, header, keyValues, new HttpCallback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo result) {
               //Log.e("name",result.getWeatherInfo().getCity())  ;
            }

            @Override
            public void onError(Exception error) {

            }
        });
    }
}
