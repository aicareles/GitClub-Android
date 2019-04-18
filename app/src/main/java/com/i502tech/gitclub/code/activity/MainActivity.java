package com.i502tech.gitclub.code.activity;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i502tech.gitclub.R;
import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.base.BaseActivity;
import com.i502tech.gitclub.base.mvvm.Resource;
import com.i502tech.gitclub.code.adapter.ArticleAdapter;
import com.i502tech.gitclub.code.bean.Article;
import com.i502tech.gitclub.code.view.FloatingActionLayout;
import com.i502tech.gitclub.code.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Inject
    ArticleViewModel articleViewModel;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    @BindView(R.id.floatinglayout)
    FloatingActionLayout floatingActionLayout;
    @BindView(R.id.tv_num)
    TextView tvNum;

    private ArticleAdapter mAdapter;
    private int page = 0;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void bindData() {
        mAdapter = new ArticleAdapter(new ArrayList<Article>(), this);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void dataObserver() {
        articleViewModel.getArticleList("0","10")
                .getArticleLiveData()
                .observe(this, response -> {
                    refreshlayout.setRefreshing(false);
                    if (response.isSuccess()){
                        if (page == 0) {
                            mAdapter.setNewData(response.data);
                        }else {
                            mAdapter.addData(response.data);
                        }
                    }else {
                        toast(response.msg);
                    }
                });
        articleViewModel.getArticleTotals()
                .getSumLiveData()
                .observe(this, resource -> {
                    if (resource.isSuccess()){
                        tvNum.setText(new StringBuilder().append("点击搜索，已收录").append(resource.data).append("个开源项目"));
                    }
                });
    }

    @Override
    protected void bindListener() {
        super.bindListener();
        refreshlayout.setOnRefreshListener(() -> {
            page = 0;
            articleViewModel.getArticleTotals();
            articleViewModel.getArticleList(String.valueOf(page), String.valueOf(10));
        });
        mAdapter.setOnLoadMoreListener(() -> {
            page++;
            articleViewModel.getArticleList(String.valueOf(page), String.valueOf(10));
        }, recyclerview);
    }

    @OnClick(R.id.ll_search)
    public void onViewClicked() {
        toActivity(SearchActivity.class);
    }
}
