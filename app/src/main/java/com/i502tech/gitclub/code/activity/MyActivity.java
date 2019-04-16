package com.i502tech.gitclub.code.activity;

import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.i502tech.gitclub.R;
import com.i502tech.gitclub.base.BaseActivity;
import com.i502tech.gitclub.code.adapter.FragmentAdapter;
import com.i502tech.gitclub.code.fragment.ArticleTypeFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MyActivity extends BaseActivity {

    private static final String TAG = "MyActivity";
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    protected final List<String> sTitle = Arrays.asList("收藏", "贡献");


    @Override
    protected int layoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void bindData() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(ArticleTypeFragment.newInstance(0), sTitle.get(0));
        fragmentAdapter.addFragment(ArticleTypeFragment.newInstance(1), sTitle.get(1));
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

}
