package com.i502tech.gitclub;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.i502tech.gitclub.app.AppConfig;
import com.i502tech.gitclub.code.di.component.ApplicationComponent;
import com.i502tech.gitclub.code.di.component.DaggerApplicationComponent;
import com.i502tech.gitclub.code.di.module.ApplicationModule;
import com.i502tech.gitclub.utils.LogUtils;
import com.thejoyrun.aptpreferences.AptParser;
import com.thejoyrun.aptpreferences.AptPreferencesManager;

import javax.inject.Inject;


/**
 * Created by jerry on 2018/12/19.
 */

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static App myApplication;

    @Inject
    Gson gson;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        myApplication = this;
        LogUtils.logInit(AppConfig.LOG_DEBUG);

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

        AptPreferencesManager.init(this, new AptParser() {
            @Override
            public Object deserialize(Class clazz, String text) {
                return gson.fromJson(text,clazz);
            }
            @Override
            public String serialize(Object object) {
                return gson.toJson(object);
            }
        });
    }

    public static App getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return mContext;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
