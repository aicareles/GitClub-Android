package com.i502tech.gitclub.code.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

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

    public ArticleViewModel getHotArticle(){
        articleRepository.getHotArticles();
        return this;
    }

    public void query(String page, String size, String query) {
        articleRepository.query(page, size, query);
    }

    public MutableLiveData<List<Article>> getArticleLiveData() {
        return articleRepository.articleLiveData;
    }

    public MutableLiveData<Integer> getSumLiveData() {
        return articleRepository.sumLiveData;
    }

    public MutableLiveData<List<String>> getHotLiveData() {
        return articleRepository.hotLiveData;
    }

    public MutableLiveData<List<Article>> getQueryLiveData() {
        return articleRepository.queryLiveData;
    }
}
