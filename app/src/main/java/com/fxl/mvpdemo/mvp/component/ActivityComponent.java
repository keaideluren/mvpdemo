package com.fxl.mvpdemo.mvp.component;

import android.support.v7.app.AppCompatActivity;

import com.fxl.mvpdemo.mvp.ActivityScope;
import com.fxl.mvpdemo.mvp.module.ActivityModule;
import com.fxl.mvpdemo.pages.activitylist.ActivityListActivity;
import com.fxl.mvpdemo.pages.amusedetail.AmuseDetailActivity;
import com.fxl.mvpdemo.pages.guid.GuidActivity;
import com.fxl.mvpdemo.pages.home.HomeActivity;
import com.fxl.mvpdemo.pages.login.LoginActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 *
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    AppCompatActivity getActivity();

    void inject(GuidActivity activity);
    void inject(LoginActivity activity);
    void inject(HomeActivity activity);
    void inject(ActivityListActivity activity);
    void inject(AmuseDetailActivity activity);
}
