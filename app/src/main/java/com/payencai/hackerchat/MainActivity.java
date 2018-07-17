package com.payencai.hackerchat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.payencai.library.http.task.HttpCallback;
import com.payencai.library.http.task.HttpTask;
import com.payencai.library.http.task.ICallBack;
import com.payencai.library.http.task.KeyValue;
import com.payencai.library.util.LogUtil;
import com.payencai.library.util.ToastUtil;
import com.payencai.library.view.BottomBar;
import com.payencai.library.view.BottomBarTab;
import com.payencai.library.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import customview.ConfirmDialog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import util.UpdateAppUtils;

public class MainActivity extends AppCompatActivity {
    String newurl="http://www.micaiqb.com:8080/download/cn.micaiw.mobile_2.apk";
    String version="";
    BottomBar mBottomBar;
    String url="http://www.weather.com.cn/data/sk/101110101.html";
    String url1="http://47.106.164.34/memen/aidKnowController/getAidKnowByManage?page=1&type=1";
    String url2="http://47.106.164.34/memen/aidKnowController/getAidKnowByManage?page=2&type=1";
    int page=1;
    int type=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomBar=(BottomBar) findViewById(R.id.bottombar);
        mBottomBar.addItem(new BottomBarTab(this,R.mipmap.ic_launcher,"天文"))
                .addItem(new BottomBarTab(this,R.mipmap.ic_launcher,"地理"))
                .addItem(new BottomBarTab(this,R.mipmap.ic_launcher,"数学"))
                .addItem(new BottomBarTab(this,R.mipmap.ic_launcher,"化学"));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                //getSupportDelegate().showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        doHttpRequest();
    }

    private void checkAndUpdate() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            update(version);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                update(version);
            } else {//申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    //根据versionName判断跟新
    private void update(String version) {
        Log.e("aaa",newurl);
        UpdateAppUtils.from(this)
                .checkBy(UpdateAppUtils.CHECK_BY_VERSION_NAME)
                .serverVersionName(version)
                .serverVersionCode(Integer.parseInt(version.substring(0,1)))
                .apkPath(newurl)
                .downloadBy(UpdateAppUtils.DOWNLOAD_BY_APP)
                .isForce(false)
                .update();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    update(version);
                } else {
                    new ConfirmDialog(this, new feature.Callback() {
                        @Override
                        public void callback(int position) {
                            if (position==1){
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + "")); // 根据包名打开对应的设置界面
                                startActivity(intent);
                            }
                        }
                    }).setContent("暂无读写SD卡权限\\n是否前往设置？").show();
                    //.setContent("暂无读写SD卡权限\n是否前往设置？").show();
                }
                break;
        }

    }
    private void doHttpRequest() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                 emitter.onNext(1);
                 emitter.onNext(2);
                 emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            int count=0;
            @Override
            public void onSubscribe(Disposable d) {
                 //观测者和被观测者订阅函数，这个方法先于发射事件方法调用。
            }

            @Override
            public void onNext(Integer integer) {
                count++;
                List<KeyValue> keyValues=new ArrayList<>();
                Object header=null;
                String url="";
                if(integer==1){
                    url=url1;
                }else{
                    url=url2;
                }
                HttpTask.getInstance().doget(url, header, keyValues, new ICallBack() {
                    @Override
                    public void success(String result) {
                        LogUtil.e("main",result);
                    }

                    @Override
                    public void failed(Exception error) {

                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                LogUtil.e("count",count+"");
            }
        });

    }
}
