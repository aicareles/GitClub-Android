package com.i502tech.gitclub.api.http.api.subscriber;

import android.util.Log;

import com.i502tech.gitclub.api.http.ResultException;
import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.base.mvvm.Resource;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 实体类的请求
 *
 * @param <T>
 */
public class BaseSubscriber<T> implements Observer<BaseResponse<T>> {

    private static final String TAG = "BaseSubscriber";

    private ResponseListener<Resource<T>> mListener;
    //用于取消连接
    private Disposable mDisposeable;

    public BaseSubscriber(ResponseListener<Resource<T>> listener) {
        this.mListener = listener;
    }

    @Override
    public void onError(Throwable e) {
        String msg = "";
        if (e instanceof SocketTimeoutException) {
            msg = "请检查您的网络设置";
        } else if (e instanceof ConnectException|| e instanceof HttpException) {
            msg = "请检查您的网络设置";
        } else if (e instanceof ResultException) {
            msg = ((ResultException) e).getMsg();
        }
        mListener.onResponse(Resource.<T>error(msg));
    }

    @Override
    public void onComplete() {
        Log.i(TAG, "onCompleted: 获取数据完成！");
    }

    @Override
    public void onSubscribe(Disposable d) {
        //拿到Disposable对象可随时取消请求
        mDisposeable = d;
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (mListener != null) {
            if (response.isSuccess()) {
                mListener.onResponse(Resource.success(response.data));
            } else {
                mListener.onResponse(Resource.<T>error(response.msg));
            }
        }
    }

}
