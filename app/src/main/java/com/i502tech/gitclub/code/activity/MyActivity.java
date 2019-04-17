package com.i502tech.gitclub.code.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.i502tech.gitclub.R;
import com.i502tech.gitclub.app.SettingsPreferences;
import com.i502tech.gitclub.base.BaseActivity;
import com.i502tech.gitclub.code.adapter.FragmentAdapter;
import com.i502tech.gitclub.code.fragment.ArticleTypeFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MyActivity extends BaseActivity {

    private static final String TAG = "MyActivity";
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int layoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void bindData() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(ArticleTypeFragment.newInstance(0), "收藏");
        fragmentAdapter.addFragment(ArticleTypeFragment.newInstance(1), "贡献");
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.btn_logout)
    public void onViewClicked() {
        SettingsPreferences.get().setUser(null);
        Intent intent_login = new Intent(MyActivity.this,LoginActivity.class);
        intent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent_login);
        finish();
    }
}
