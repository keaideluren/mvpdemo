package com.fxl.mvpdemo.model.network;

import android.support.annotation.NonNull;

import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.util.JsonParam;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/16.
 * 请求网络工具，Retrofit线程切换及参数拼接等及缓存控制等
 */

public abstract class RetrofitRequestUtil<D> {
    private RetrofitDefaultSubscriber<D> subscriber;

    public RetrofitRequestUtil request(@NonNull JsonParam param, @NonNull RetrofitDefaultSubscriber<D> subscriber) {
        this.subscriber = subscriber;
        Observable.create((ObservableOnSubscribe<JsonParam>) e -> {
            e.onNext(param);
            e.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(jsonParam -> getRequest(jsonParam.getMap(), jsonParam.getCacheKey()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return this;
    }

    public void stopRequest() {
        subscriber.unSubscriber();
    }

    public abstract Observable<BaseEntity<D>> getRequest(HashMap<String, Object> param, String cacheKey);

}
