package com.i502tech.gitclub.code.di;

import com.i502tech.gitclub.code.activity.MainActivity;
import com.i502tech.gitclub.code.activity.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
@Singleton//注意：写在Component的上面
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(SearchActivity searchActivity);
}
