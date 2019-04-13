package com.i502tech.gitclub.code.viewmodel;


import android.arch.lifecycle.ViewModel;
import com.i502tech.gitclub.code.repository.ArticleRepository;
import javax.inject.Inject;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
public class ArticleViewModel extends ViewModel {
    ArticleRepository articleRepository;

    @Inject
    public ArticleViewModel(ArticleRepository repository) {
        this.articleRepository = repository;
    }

    public void getArticleList(String page, String size) {
        articleRepository.getArticleList(page, size);
    }

    public void getArticleTotals(){
        articleRepository.getArticleTotals();
    }

    public void query(String page, String size, String query) {
        articleRepository.query(page, size, query);
    }

}
