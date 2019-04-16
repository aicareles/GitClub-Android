package com.i502tech.gitclub.api;

import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.code.bean.Article;
import com.i502tech.gitclub.code.bean.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

//    @GET("getGift")
//    Observable<BaseResponse<List<GiftVO>>> getGift();

    //搜索
    @FormUrlEncoded
    @POST("login")
    Observable<BaseResponse<User>> login(@FieldMap Map<String, String> map);

    //获取文章列表
    @FormUrlEncoded
    @POST("getArticleList")
    Observable<BaseResponse<List<Article>>> getArticleList(@FieldMap Map<String, String> map);

    //获取开源库的总收录数
    @POST("getArticleTotals")
    Observable<BaseResponse<Integer>> getArticleTotals();

    //关键字查询
    @FormUrlEncoded
    @POST("query")
    Observable<BaseResponse<List<Article>>> query(@FieldMap Map<String, String> map);

    //我的点赞(收藏)文章列表
    @FormUrlEncoded
    @POST("getMyStarArticles")
    Observable<BaseResponse<List<Article>>> getMyStarArticles(@FieldMap Map<String, String> map);

    //获取我贡献的文章列表
    @FormUrlEncoded
    @POST("getMyContributeArticles")
    Observable<BaseResponse<List<Article>>> getMyContributeArticles(@FieldMap Map<String, String> map);

}
