package com.i502tech.gitclub.code.di.component;

import com.i502tech.gitclub.code.activity.LoginActivity;
import com.i502tech.gitclub.code.activity.MainActivity;
import com.i502tech.gitclub.code.activity.MyActivity;
import com.i502tech.gitclub.code.activity.SearchActivity;
import com.i502tech.gitclub.code.di.ActivityScope;
import com.i502tech.gitclub.code.di.module.ActivityModule;
import com.i502tech.gitclub.code.fragment.ArticleTypeFragment;

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
    void inject(MyActivity myActivity);
    void inject(ArticleTypeFragment articleTypeFragment);
    void inject(LoginActivity loginActivity);
}
