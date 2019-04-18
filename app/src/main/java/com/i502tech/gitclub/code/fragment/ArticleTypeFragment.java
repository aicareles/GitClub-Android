package com.i502tech.gitclub.code.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i502tech.gitclub.R;
import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.base.BaseFragment;
import com.i502tech.gitclub.code.activity.MyActivity;
import com.i502tech.gitclub.code.adapter.ArticleTypeAdapter;
import com.i502tech.gitclub.code.bean.Article;
import com.i502tech.gitclub.code.viewmodel.ArticleViewModel;
import com.i502tech.gitclub.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * description $desc$
 * created by jerry on 2019/4/16.
 */
public class ArticleTypeFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Inject
    ArticleViewModel articleViewModel;

    private ArticleTypeAdapter mAdapter;
    private int page;
    private int mType = 0;

    //收藏", "贡献  0-1

    public static ArticleTypeFragment newInstance(int type) {
        ArticleTypeFragment fragment = new ArticleTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_article_type;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void bindData() {
        mType = Objects.requireNonNull(getArguments()).getInt("type");
        mAdapter = new ArticleTypeAdapter(new ArrayList<Article>(), context);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();
        articleViewModel.getTypeArticles(mType, String.valueOf(page), String.valueOf("10"), String.valueOf("3"))
                .getTypeLiveData()
                .observe(this, new Observer<BaseResponse<List<Article>>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<List<Article>> response) {
                        if (response.isSuccess()){
                            if (page == 0) {
                                mAdapter.setNewData(response.data);
                            }else {
                                mAdapter.addData(response.data);
                            }
                        }else {
                            ToastUtil.show(response.msg);
                        }
                    }
                });
    }

    @Override
    protected void bindListener() {

    }

}
