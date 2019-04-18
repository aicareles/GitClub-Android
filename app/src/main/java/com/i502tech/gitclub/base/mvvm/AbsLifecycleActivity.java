package com.i502tech.gitclub.base.mvvm;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.i502tech.gitclub.App;
import com.i502tech.gitclub.code.di.component.ActivityComponent;
import com.i502tech.gitclub.code.di.component.DaggerActivityComponent;
import com.i502tech.gitclub.code.di.module.ActivityModule;
import com.i502tech.gitclub.code.event.LiveBus;

import java.util.ArrayList;
import java.util.List;

/**
 * description $desc$
 * created by jerry on 2019/4/18.
 */
public abstract class AbsLifecycleActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;
    private List<Object> eventKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(App.getInstance().getApplicationComponent())
                .build();
        initInject();
        dataObserver();
    }

    /**
     * 初始化依赖注入
     */
    protected void initInject(){}

    protected void dataObserver() {}

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected <T> MutableLiveData<T> registerObserver(Object eventKey, Class<T> tClass) {

        return registerObserver(eventKey, null, tClass);
    }

    protected <T> MutableLiveData<T> registerObserver(Object eventKey, String tag, Class<T> tClass) {
        String event;
        if (TextUtils.isEmpty(tag)) {
            event = (String) eventKey;
        } else {
            event = eventKey + tag;
        }
        eventKeys.add(event);
        return LiveBus.getDefault().subscribe(eventKey, tag, tClass);
    }

    protected <T> MutableLiveData<T> registerObserver(Object eventKey, T t) {
        eventKeys.add(eventKey);
        return LiveBus.getDefault().subscribe(eventKey, null, t);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventKeys != null && eventKeys.size() > 0) {
            for (int i = 0; i < eventKeys.size(); i++) {
                LiveBus.getDefault().clear(eventKeys.get(i));
            }
        }
    }
}
