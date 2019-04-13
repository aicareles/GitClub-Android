package com.i502tech.gitclub.api;


import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.api.http.api.subscriber.BeanSubscriber;
import com.i502tech.gitclub.api.http.api.subscriber.SubscriberListener;
import com.i502tech.gitclub.app.C;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitManager {
    private static RetrofitManager mInstance;
    public static OkHttpClient mOkHttpClient = OkHttpManager.getOkHttpClient();
    private static final String TAG = "RetrofitManager";
    private static final Retrofit mRetrofit = new Retrofit.Builder()
            //默认网络请求
//                .client(builder.build())
            //设置使用okhttp网络请求
            .client(mOkHttpClient)
            .baseUrl(C.Constance.APP_HOST)
//                .addConverterFactory(ResponseConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static ApiService mApiService = mRetrofit.create(ApiService.class);

    /**
     * 私有构造方法
     */
    private RetrofitManager() {
        if (null == mOkHttpClient) {
            mOkHttpClient = OkHttpManager.getOkHttpClient();
        }
//        mRetrofit = new Retrofit.Builder()
//                //默认网络请求
////                .client(builder.build())
//                //设置使用okhttp网络请求
//                .client(mOkHttpClient)
//                .baseUrl(C.Constance.APP_HOST)
////                .addConverterFactory(ResponseConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
    }

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                mInstance = new RetrofitManager();
            }
        }
        return mInstance;
    }

    public ApiService getApiService() {
        return mApiService;
    }

    //请求的原始函数
    private static <T> void request(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static <T> void ApiFunc(Observable<BaseResponse<T>> observable, SubscriberListener<BaseResponse<T>> listener) {
        request(observable,new BeanSubscriber<T>(listener));
    }


}
