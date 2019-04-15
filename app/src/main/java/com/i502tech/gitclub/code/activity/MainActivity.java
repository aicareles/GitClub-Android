package com.i502tech.gitclub.code.activity;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i502tech.gitclub.R;
import com.i502tech.gitclub.base.BaseActivity;
import com.i502tech.gitclub.code.adapter.ArticleAdapter;
import com.i502tech.gitclub.code.bean.Article;
import com.i502tech.gitclub.code.bean.User;
import com.i502tech.gitclub.code.view.FloatingActionLayout;
import com.i502tech.gitclub.code.viewmodel.ArticleViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Inject
    ArticleViewModel articleViewModel;

    @Inject
    ArticleViewModel articleViewModel2;

    @Inject
    User user;

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
        mAdapter = new ArticleAdapter(articleViewModel.getArticleLiveData().getValue(), this);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setAdapter(mAdapter);
        user.setNick_name("刘磊");
    }

    @Override
    protected void dataObserver() {
        articleViewModel.getArticleList("0","10")
                .getArticleLiveData()
                .observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                if (page == 0) {
                    refreshlayout.setRefreshing(false);
                    mAdapter.setNewData(articles);
                }else {
                    mAdapter.addData(articles);
                }
            }
        });
        articleViewModel2.getArticleTotals()
                .getSumLiveData()
                .observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                tvNum.setText("点击搜索，已收录" + integer + "个开源项目");
            }
        });

    }

    @Override
    protected void bindListener() {
        super.bindListener();
        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                articleViewModel.getArticleList(String.valueOf(page), String.valueOf(10));
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                articleViewModel.getArticleList(String.valueOf(page), String.valueOf(10));
            }
        }, recyclerview);
    }

    @OnClick(R.id.ll_search)
    public void onViewClicked() {
        toActivity(SearchActivity.class);
    }
}
