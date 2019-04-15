package com.i502tech.gitclub.code.di.component;

import android.content.Context;

import com.google.gson.Gson;
import com.i502tech.gitclub.App;
import com.i502tech.gitclub.code.bean.User;
import com.i502tech.gitclub.code.di.ApplicationContext;
import com.i502tech.gitclub.code.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App myApplication);

    @ApplicationContext
    Context getContext();

    User getUser();

    Gson getGson();

}
