package com.fxl.mvpdemo.myutils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/7/20.
 * 用RxJava对控件进行防抖等操作
 */

public class RxViewUtil {
    private static HashMap<Integer, ObservableEmitter<Integer>> observableMap = new HashMap<>();

    public static void filterShake(final int id) {
        if (id == 0) {
            throw new IllegalArgumentException("控件必须有Id");
        }
        if(observableMap.containsKey(id)) {
            observableMap.get(id).onNext(id);
        } else {
            Observable.create(new ObservableOnSubscribe<Integer>() {
                @Override
                public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                    observableMap.put(id, e);
                    e.onNext(id);
                }
            })
            .throttleFirst(500, TimeUnit.SECONDS)
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {

                }
            });
        }
    }

    public static void removeFilterShake(int id) {
        observableMap.remove(id);
    }
}
