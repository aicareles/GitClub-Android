package com.i502tech.gitclub;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.i502tech.gitclub.app.AppConfig;
import com.i502tech.gitclub.utils.LogUtils;


/**
 * Created by jerry on 2018/12/19.
 */

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        LogUtils.logInit(AppConfig.LOG_DEBUG);

    }

    public static Context getContext() {
        return mContext;
    }

}
