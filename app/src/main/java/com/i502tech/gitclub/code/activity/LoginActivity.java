package com.i502tech.gitclub.code.activity;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.i502tech.gitclub.R;
import com.i502tech.gitclub.app.SettingsPreferences;
import com.i502tech.gitclub.base.BaseActivity;
import com.i502tech.gitclub.code.bean.User;
import com.i502tech.gitclub.code.viewmodel.UserViewModel;
import com.i502tech.gitclub.utils.BeanUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description $desc$
 * created by jerry on 2019/4/16.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.et_passward)
    EditText etPassward;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Inject
    UserViewModel userViewModel;

    @Inject
    User userInfo;

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void bindData() {
        User user = SettingsPreferences.get().getUser();
        if (user != null) {
            BeanUtils.copyProperties(user, userInfo);
            toActivity(MainActivity.class);
            finish();
        }
    }

    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty(etCount.getText()) || TextUtils.isEmpty(etPassward.getText())){
                    toast("用户名或密码不能为空");
                    return;
                }
                userViewModel.loginRegister(btnLogin.getText().toString(), etCount.getText().toString(), etPassward.getText().toString())
                        .getUser()
                        .observe(this, new Observer<User>() {
                            @Override
                            public void onChanged(@Nullable User user) {
                                BeanUtils.copyProperties(user, userInfo);
                                SettingsPreferences.get().setUser(user);
                                toActivity(MainActivity.class);
                                finish();
                            }
                        });
                break;
            case R.id.tv_register:
                if ("注册".equals(tvRegister.getText().toString())){
                    tvRegister.setText("登录");
                    btnLogin.setText("注册");
                }else {
                    tvRegister.setText("注册");
                    btnLogin.setText("登录");
                }
                break;
        }
    }
}
