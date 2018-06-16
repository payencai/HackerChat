package com.payencai.hackerchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.payencai.library.util.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtil.showToast(this,"hello world");
    }
}
