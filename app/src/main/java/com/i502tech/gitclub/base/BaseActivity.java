package com.i502tech.gitclub.base;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.i502tech.gitclub.R;
import com.i502tech.gitclub.app.language.LanguageUtil;
import com.i502tech.gitclub.base.mvvm.AbsLifecycleActivity;
import com.i502tech.gitclub.utils.GlobalStatusBarUtil;
import com.i502tech.gitclub.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: arze
 * Date: 2018/4/8
 * Time: 23:28
 */

public abstract class BaseActivity extends AbsLifecycleActivity {

    public Toolbar toolbar;
    public TextView abTitle;
    private AlertDialog mConnectDialog;
    private Unbinder unbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏虚拟键
//        hideBottomUIMenu();
        setTranslucentStatus();
        setContentView(layoutId());
        GlobalStatusBarUtil.setFitsSystemWindows(this, true);
        initToolBar();
        unbind = ButterKnife.bind(this);
        bindData();
        bindListener();
    }

    protected abstract int layoutId();

    protected abstract void bindData();

    protected void bindListener(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind.unbind();
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
