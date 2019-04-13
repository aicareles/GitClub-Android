package com.i502tech.gitclub.code.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.i502tech.gitclub.api.HttpUtils;
import com.i502tech.gitclub.api.RetrofitManager;
import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.api.http.api.subscriber.SubscriberListener;
import com.i502tech.gitclub.code.bean.User;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
@Singleton
public class UserRepository {

//    @Inject
//    public UserRepository(){}  //此方式不需要module

    public LiveData<User> login(String nickName, String password){
        final MutableLiveData<User> data = new MutableLiveData<>();
        Map<String, String> map = new HashMap<>();
        map.put("userName", nickName);
        map.put("password", password);
        HttpUtils.ApiFunc(RetrofitManager.mApiService.login(map), new SubscriberListener<BaseResponse<User>>() {
            @Override
            public void onSuccess(BaseResponse<User> userBaseResponse) {
                data.setValue(userBaseResponse.data);
            }

            @Override
            public void onFailer(String msg) {
                Log.e("onFailer", "onFailer: ");
            }

            @Override
            public void onTokenError() {

            }
        });
        return data;
    }
}
