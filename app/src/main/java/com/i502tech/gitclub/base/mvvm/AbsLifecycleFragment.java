package com.i502tech.gitclub.base.mvvm;


import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i502tech.gitclub.App;
import com.i502tech.gitclub.code.di.component.ActivityComponent;
import com.i502tech.gitclub.code.di.component.DaggerActivityComponent;
import com.i502tech.gitclub.code.di.module.ActivityModule;
import com.i502tech.gitclub.code.event.Event;
import com.i502tech.gitclub.code.event.LiveBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbsLifecycleFragment extends Fragment {

    private ActivityComponent mActivityComponent;
    protected Context context;
    protected Activity activity;
    private List<Object> eventKeys = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = getContext();
        activity = getActivity();
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule((AppCompatActivity) activity))
                .applicationComponent((App.getInstance().getApplicationComponent()))
                .build();
        initInject();
        registerObserver(Event.EVENT_KEY_FAIL, String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (!TextUtils.isEmpty(s)){
                    loadError(s);
                }
            }
        });
    }

    /**
     * 初始化依赖注入
     */
    protected abstract void initInject();

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
    public void onDestroyView() {
        super.onDestroyView();
        if (eventKeys != null && eventKeys.size() > 0) {
            for (int i = 0; i < eventKeys.size(); i++) {
                LiveBus.getDefault().clear(eventKeys.get(i));
            }
        }
    }

    protected void loadError(String msg){}
}
