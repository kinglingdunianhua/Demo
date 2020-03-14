package com.example.newdemo.app;

import android.app.Application;
import android.content.Context;

import com.xuexiang.xui.XUI;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    public static Context getContext() {
        return mContext;
    }

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;

        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
        //初始化
        initokHttpClient();
    }

    private void initokHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }
}
