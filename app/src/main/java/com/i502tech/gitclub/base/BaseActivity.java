package com.i502tech.gitclub.base;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.i502tech.gitclub.App;
import com.i502tech.gitclub.R;
import com.i502tech.gitclub.app.language.LanguageUtil;
import com.i502tech.gitclub.code.di.component.ActivityComponent;
import com.i502tech.gitclub.code.di.component.DaggerActivityComponent;
import com.i502tech.gitclub.code.di.module.ActivityModule;
import com.i502tech.gitclub.code.event.LiveBus;
import com.i502tech.gitclub.utils.GlobalStatusBarUtil;
import com.i502tech.gitclub.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: arze
 * Date: 2018/4/8
 * Time: 23:28
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public TextView abTitle;
    private AlertDialog mConnectDialog;
    private Unbinder unbind;
    private ActivityComponent mActivityComponent;
    private List<Object> eventKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏虚拟键
//        hideBottomUIMenu();
        setTranslucentStatus();
        setContentView(layoutId());
        GlobalStatusBarUtil.setFitsSystemWindows(this, true);
        initToolBar();
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(App.getInstance().getApplicationComponent())
                .build();
        initInject();
        dataObserver();
        unbind = ButterKnife.bind(this);
        bindData();
        bindListener();
    }

    protected abstract int layoutId();

    /**
     * 初始化依赖注入
     */
    protected abstract void initInject();

    protected abstract void bindData();

    protected void dataObserver() {}

    protected void bindListener(){}

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
        unbind.unbind();
        if (eventKeys != null && eventKeys.size() > 0) {
            for (int i = 0; i < eventKeys.size(); i++) {
                LiveBus.getDefault().clear(eventKeys.get(i));
            }
        }
    }

    protected void toActivity(@NonNull Class cl) {
        startActivity(new Intent(this, cl));
    }

    protected void toActivity(@NonNull Class cl, Bundle bundle) {
        Intent intent = new Intent(this, cl);
        intent.putExtra(cl.getSimpleName(), bundle);
        startActivity(intent);
    }

    protected View inflate(int layoutIds) {
        return LayoutInflater.from(this).inflate(layoutIds, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, LanguageUtil.getSaveLanguage(newBase)));
    }

    //自己新添加的
    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (abTitle != null) {
            abTitle.setText(title);
        }
    }

    //自己新添加的
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            abTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        }
        if (abTitle != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void setTranslucentStatus() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        GlobalStatusBarUtil.setStatusColor(this, getStatusBarColorId());
        GlobalStatusBarUtil.setStatusBarDarkFont(this, isDarkFont());
    }


    /**
     * 可以重写状态栏的颜色
     *
     * @return
     */
    protected int getStatusBarColorId() {
        return ContextCompat.getColor(this, R.color.colorPrimary);
    }

    /**
     * 是否设置状态栏字体黑色
     * @return
     */
    protected boolean isDarkFont() {
        return false;
    }

    /**
     * hide inputMethod
     */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    /**
     * show inputMethod
     */
    public void showSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(null != imm) {
            v.setFocusable(true);
            v.setFocusableInTouchMode(true);
            v.requestFocus();
            imm.showSoftInput(v, 0);
        }
    }

    //显示连接设备对话框
    public void showDialog(String tipContext) {
        if (mConnectDialog == null) {
            mConnectDialog = new AlertDialog.Builder(this).setCancelable(false).create();
            mConnectDialog.setCanceledOnTouchOutside(false);
            mConnectDialog.show();
//            mConnectDialog.setContentView(R.layout.progress_dialog);
        }
        TextView message = mConnectDialog.findViewById(R.id.message);
        message.setText(tipContext);
        mConnectDialog.show();
    }

    //隐藏对话框
    public void dismissDialog(){
        if (mConnectDialog != null){
            mConnectDialog.dismiss();
        }
    }

    public void toast(int resid){
        Toast.makeText(this, resid, Toast.LENGTH_SHORT).show();
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected Dialog mDialog;
    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        if (null == mDialog) {
//            mDialog = DialogUtil.getCenterProgressDialog(this);
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
//            mDialog = DialogUtil.getCenterProgressDialog(this, msg, true);
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

    public Activity getActivity(){
        return BaseActivity.this;
    }

    public void showCentToast(String msg){
        ToastUtil.centerToast(this,msg);
    }
}
