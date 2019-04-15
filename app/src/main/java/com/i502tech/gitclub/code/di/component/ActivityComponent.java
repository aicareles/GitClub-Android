package com.i502tech.gitclub.code.di.component;

import com.i502tech.gitclub.code.activity.MainActivity;
import com.i502tech.gitclub.code.activity.SearchActivity;
import com.i502tech.gitclub.code.di.ActivityScope;
import com.i502tech.gitclub.code.di.module.ActivityModule;
import dagger.Component;

/**
 * description $desc$
 * created by jerry on 2019/4/11.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(SearchActivity searchActivity);
}
