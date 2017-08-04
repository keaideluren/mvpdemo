package com.fxl.mvpdemo.mvp.component;

import android.content.Context;

import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.mvp.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 *
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context ApplicationContext();  // 提供App的Context
//
    RetrofitHelper retrofitHelper();  //提供http的帮助类

}
