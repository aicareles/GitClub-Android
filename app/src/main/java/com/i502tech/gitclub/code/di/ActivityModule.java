package com.i502tech.gitclub.code.di;

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

    @Singleton
    @Provides
    UserRepository providesUserRepository(){
        return new UserRepository();
    }

    @Singleton
    @Provides
    ArticleRepository providesArticleRepository(){
        return new ArticleRepository();
    }
}
