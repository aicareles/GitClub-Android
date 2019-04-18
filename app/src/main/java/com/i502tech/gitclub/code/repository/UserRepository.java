package com.i502tech.gitclub.code.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.i502tech.gitclub.api.HttpUtils;
import com.i502tech.gitclub.api.RetrofitManager;
import com.i502tech.gitclub.api.http.api.BaseResponse;
import com.i502tech.gitclub.api.http.api.subscriber.ResponseListener;
import com.i502tech.gitclub.api.http.api.subscriber.SubscriberListener;
import com.i502tech.gitclub.base.mvvm.Resource;
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

    public LiveData<Resource<User>> login(String nickName, String password){
        final MutableLiveData<Resource<User>> data = new MutableLiveData<>();
        Map<String, String> map = new HashMap<>();
        map.put("userName", nickName);
        map.put("password", password);
        HttpUtils.ApiFunc(RetrofitManager.mApiService.login(map), response -> data.setValue(response));
        return data;
    }

    public LiveData<Resource<User>> register(String username, String password){
        final MutableLiveData<Resource<User>> data = new MutableLiveData<>();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        HttpUtils.ApiFunc(RetrofitManager.mApiService.login(map), response -> data.setValue(response));
        return data;
    }
}
