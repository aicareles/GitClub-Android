package com.i502tech.gitclub.base;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i502tech.gitclub.base.mvvm.AbsLifecycleFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends AbsLifecycleFragment {

    protected View mRootView;
    private Unbinder mUnBinder;

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
        dataObserver();
        return mRootView;
    }

    protected abstract int layoutId();

    protected abstract void bindData();

    protected abstract void bindListener();

    protected void dataObserver() {}

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
