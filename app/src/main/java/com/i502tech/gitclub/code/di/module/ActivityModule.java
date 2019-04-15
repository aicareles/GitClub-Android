package com.i502tech.gitclub.code.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.i502tech.gitclub.code.di.ActivityScope;
import com.i502tech.gitclub.code.repository.ArticleRepository;
import com.i502tech.gitclub.code.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
@Module
public class ActivityModule {

    private AppCompatActivity mAppCompatActivity;

    public ActivityModule(AppCompatActivity mAppCompatActivity) {
        this.mAppCompatActivity = mAppCompatActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mAppCompatActivity;
    }

    @Provides
    Context providesContext() {
        return mAppCompatActivity;
    }

    @Provides
    UserRepository providesUserRepository(){
        return new UserRepository();
    }

    @Provides
    ArticleRepository providesArticleRepository(){
        return new ArticleRepository();
    }
}
