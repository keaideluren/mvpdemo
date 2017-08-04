package com.fxl.mvpdemo.mvp.base;

import android.support.annotation.CallSuper;

import com.fxl.mvpdemo.mvp.MVP;

/**
 * Created by codeest on 2016/8/2.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public abstract class RxPresenter<T extends MVP.BaseView> implements MVP.BasePresenter<T> {

    protected T mView;
    protected String TAG;

    @Override
    @CallSuper
    public void attachView(T view) {
        this.mView = view;
        TAG = this.getClass().getSimpleName();
    }

    @Override
    @CallSuper
    public void detachView() {
        this.mView = null;
        stopNetWork();
    }

    public abstract void stopNetWork();
}
