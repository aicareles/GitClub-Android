package com.i502tech.gitclub.code.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.i502tech.gitclub.api.HttpUtils;
import com.i502tech.gitclub.api.RetrofitManager;
import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.api.http.api.subscriber.SubscriberListener;
import com.i502tech.gitclub.base.BaseRepository;
import com.i502tech.gitclub.code.bean.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
@Singleton
public class ArticleRepository extends BaseRepository {
    public MutableLiveData<List<Article>> articleLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> sumLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Article>> queryLiveData = new MutableLiveData<>();

    public void getArticleList(String page, String size) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        HttpUtils.ApiFunc(RetrofitManager.mApiService.getArticleList(map), new SubscriberListener<BaseResponse<List<Article>>>() {
            @Override
            public void onSuccess(BaseResponse<List<Article>> userBaseResponse) {
                articleLiveData.setValue(userBaseResponse.data);
            }

            @Override
            public void onFailer(String msg) {
                Log.e("onFailer", "onFailer: ");
            }

            @Override
            public void onTokenError() {

            }
        });
    }

    public void getArticleTotals() {
        HttpUtils.ApiFunc(RetrofitManager.mApiService.getArticleTotals(), new SubscriberListener<BaseResponse<Integer>>() {
            @Override
            public void onSuccess(BaseResponse<Integer> integerBaseResponse) {
                sumLiveData.setValue(integerBaseResponse.data);
            }

            @Override
            public void onFailer(String msg) {

            }

            @Override
            public void onTokenError() {

            }
        });
    }

    public void query(String page, String size, String query) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        map.put("query", query);
        HttpUtils.ApiFunc(RetrofitManager.mApiService.query(map), new SubscriberListener<BaseResponse<List<Article>>>() {
            @Override
            public void onSuccess(BaseResponse<List<Article>> userBaseResponse) {
                queryLiveData.setValue(userBaseResponse.data);
            }

            @Override
            public void onFailer(String msg) {
                Log.e("onFailer", "onFailer: ");
            }

            @Override
            public void onTokenError() {

            }
        });
    }
}

