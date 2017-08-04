package com.fxl.mvpdemo.mvp.module;

import android.content.Context;

import com.fxl.mvpdemo.LeplusApplication;
import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.mvp.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */
@ContextLife
@Module
public class AppModule {
    private LeplusApplication application;

    public AppModule(LeplusApplication application) {
        this.application = application;
    }
//
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }
//
    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }
}
