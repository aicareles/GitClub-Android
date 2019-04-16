package com.i502tech.gitclub.code.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.i502tech.gitclub.code.bean.User;
import com.i502tech.gitclub.code.repository.UserRepository;

import javax.inject.Inject;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
public class UserViewModel extends ViewModel {
    private LiveData<User> user;
    private UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository repository) {
        this.userRepository = repository;
    }

    public UserViewModel loginRegister(String type, String nickName, String password) {
        if (type.equals("登录")){
            user = userRepository.login(nickName, password);
        }else {
            user = userRepository.register(nickName, password);
        }
        return this;
    }

    public LiveData<User> getUser() {
        return user;
    }
}
