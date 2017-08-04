package com.fxl.mvpdemo.mvp.module;

import android.support.v7.app.AppCompatActivity;

import com.fxl.mvpdemo.mvp.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 * Module
 */

@ActivityScope
@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public AppCompatActivity provideActivity() {
        return mActivity;
    }
}
