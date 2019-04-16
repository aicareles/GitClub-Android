package com.i502tech.gitclub.code.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.i502tech.gitclub.api.HttpUtils;
import com.i502tech.gitclub.api.RetrofitManager;
import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.api.http.api.subscriber.SubscriberListener;
import com.i502tech.gitclub.base.BaseRepository;
import com.i502tech.gitclub.code.bean.Article;

import java.util.Arrays;
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
    public MutableLiveData<List<String>> hotLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Article>> queryLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Article>> articleTypeLiveData = new MutableLiveData<>();

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
        });
    }

    public void getHotArticles(){
        List<String> hotList = Arrays.asList("自定义View", "Tab", "WebView", "图片加载", "相机",
                "图表", "列表", "数据库", "蓝牙", "视频", "网络请求", "人脸识别", "OpenGL", "Canvas", "音频", "完整项目");
        hotLiveData.setValue(hotList);
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
        });
    }

    public void getMyStarArticles(String page, String size, String user_id){
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        map.put("user_id", user_id);
        HttpUtils.ApiFunc(RetrofitManager.mApiService.getMyStarArticles(map), new SubscriberListener<BaseResponse<List<Article>>>() {
            @Override
            public void onSuccess(BaseResponse<List<Article>> userBaseResponse) {
                articleTypeLiveData.setValue(userBaseResponse.data);
            }

            @Override
            public void onFailer(String msg) {
                Log.e("onFailer", "onFailer: ");
            }
        });
    }

    public void getMyContributeArticles(String page, String size, String user_id){
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        map.put("user_id", user_id);
        HttpUtils.ApiFunc(RetrofitManager.mApiService.getMyContributeArticles(map), new SubscriberListener<BaseResponse<List<Article>>>() {
            @Override
            public void onSuccess(BaseResponse<List<Article>> userBaseResponse) {
                articleTypeLiveData.setValue(userBaseResponse.data);
            }

            @Override
            public void onFailer(String msg) {
                Log.e("onFailer", "onFailer: ");
            }
        });
    }
}

