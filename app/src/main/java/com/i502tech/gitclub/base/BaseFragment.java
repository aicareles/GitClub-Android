package com.i502tech.gitclub.base;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i502tech.gitclub.App;
import com.i502tech.gitclub.code.di.component.ActivityComponent;
import com.i502tech.gitclub.code.di.component.DaggerActivityComponent;
import com.i502tech.gitclub.code.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: arze
 * Date: 2018/4/8
 * Time: 23:28
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    private Unbinder mUnBinder;
    private ActivityComponent mActivityComponent;
    protected Context context;
    protected Activity activity;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(layoutId(), null);
            mUnBinder = ButterKnife.bind(this, mRootView);
        }
        bindData();
        bindListener();
        return mRootView;
    }

    protected abstract int layoutId();

    /**
     * 初始化依赖注入
     */
    protected abstract void initInject();

    protected abstract void bindData();

    protected abstract void bindListener();

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected void toActivity(@NonNull Class cl) {
        startActivity(new Intent(getActivity(), cl));
    }

    protected void toActivity(@NonNull Class cl, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cl);
        Log.w("Ok", cl.getSimpleName() + " or " + cl.getClass().getSimpleName());
        intent.putExtra(cl.getSimpleName(), bundle);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    protected View inflate(int resId) {
        return LayoutInflater.from(getActivity()).inflate(resId, null);
    }

    protected Dialog mDialog;
    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        if (null == mDialog) {
//            mDialog = DialogUtil.getCenterProgressDialog(getActivity());
        }
        mDialog.show();
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        if (null == mDialog) {
//            mDialog = DialogUtil.getCenterProgressDialog(getActivity(), msg, true);
        }
        mDialog.show();
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        if (null != mDialog) {
            mDialog.cancel();
        }
    }
}
