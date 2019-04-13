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
import com.i502tech.gitclub.code.event.Event;
import com.i502tech.gitclub.code.view.FloatingActionLayout;
import com.i502tech.gitclub.code.viewmodel.ArticleViewModel;

import java.util.ArrayList;

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

    private ArrayList<Article> mArticles;
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
        mArticles = new ArrayList<>();
        mAdapter = new ArticleAdapter(mArticles, this);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setAdapter(mAdapter);
        articleViewModel.getArticleList(String.valueOf(page), String.valueOf(15));
        articleViewModel.getArticleTotals();
    }

    @Override
    protected void dataObserver() {
        ArrayList<Article> articles = new ArrayList<>();
        registerObserver(Event.EVENT_KEY_ARTICLE_LIST, articles).observe(this, new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Article> articles) {
                if (page == 0) {
                    refreshlayout.setRefreshing(false);
                    mArticles.clear();
                }
                mArticles.addAll(articles);
                mAdapter.notifyDataSetChanged();
            }
        });

        registerObserver(Event.EVENT_KEY_ARTICLE_SIZE, Integer.class).observe(this, new Observer<Integer>() {
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
                articleViewModel.getArticleList(String.valueOf(page), String.valueOf(15));
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                articleViewModel.getArticleList(String.valueOf(page), String.valueOf(15));
            }
        }, recyclerview);
    }

    @OnClick(R.id.ll_search)
    public void onViewClicked() {
        toActivity(SearchActivity.class);
    }
}