package com.i502tech.gitclub.code.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.code.bean.Article;
import com.i502tech.gitclub.code.repository.ArticleRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
public class ArticleViewModel extends ViewModel {

    private ArticleRepository articleRepository;

    @Inject
    public ArticleViewModel(ArticleRepository repository) {
        this.articleRepository = repository;
    }

    public ArticleViewModel getArticleList(String page, String size) {
        articleRepository.getArticleList(page, size);
        return this;
    }

    public ArticleViewModel getArticleTotals(){
        articleRepository.getArticleTotals();
        return this;
    }

    public void query(String page, String size, String query) {
        articleRepository.query(page, size, query);
    }

    public ArticleViewModel getTypeArticles(int type, String page, String size, String user_id){
        if (type == 0){
            articleRepository.getMyStarArticles(page, size, user_id);
        }else {
            articleRepository.getMyContributeArticles(page, size, user_id);
        }
        return this;
    }

    public MutableLiveData<BaseResponse<List<Article>>> getArticleLiveData() {
        return articleRepository.articleLiveData;
    }

    public MutableLiveData<Integer> getSumLiveData() {
        return articleRepository.sumLiveData;
    }

    public MutableLiveData<BaseResponse<List<Article>>> getQueryLiveData() {
        return articleRepository.queryLiveData;
    }

    public MutableLiveData<BaseResponse<List<Article>>> getTypeLiveData() {
        return articleRepository.articleTypeLiveData;
    }
}
