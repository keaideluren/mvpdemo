package com.fxl.mvpdemo.model.network;

import com.fxl.mvpdemo.model.bean.BaseEntity;
import com.fxl.mvpdemo.mvp.MVP;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Administrator 可爱的路人 on 2017/2/23.
 * 默认网络请求完成的订阅者
 */

public abstract class RetrofitDefaultSubscriber<T> implements Observer<BaseEntity<T>> {
    private Disposable disposable;
    private MVP.BaseView baseView;
    private boolean isShowLoadingDialog;

    public RetrofitDefaultSubscriber(MVP.BaseView view, boolean isShowLoadingDialog) {
        this.isShowLoadingDialog = isShowLoadingDialog;
        this.baseView = view;
    }

    public void unSubscriber() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        if (baseView != null) {
            baseView.dismissBaseDialog(MVP.BaseView.DIALOG_TYPE_LOADING);
        }
    }

    /**
     * 默认显示加载中的Dialog菊花
     *
     * @param context 上下文
     */
    public RetrofitDefaultSubscriber(MVP.BaseView context) {
        this(context, true);
    }

    @Override
    public void onComplete() {
        if (baseView != null) {
            baseView.dismissBaseDialog(MVP.BaseView.DIALOG_TYPE_LOADING);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        if (baseView != null && isShowLoadingDialog) {
            baseView.showBaseDialog(MVP.BaseView.DIALOG_TYPE_LOADING);
        }
    }

    @Override
    public void onError(Throwable e) {
        //出错了统一使用，"网络似乎开小差了".
        baseView.showToast("网络似乎开小差了");
        if (baseView != null) {
            baseView.dismissBaseDialog(MVP.BaseView.DIALOG_TYPE_LOADING);
        }
    }

    @Override
    public void onNext(BaseEntity<T> value) {
        if (value.getState() == 1) {
            onSuccess(value.getData(), value.getMessage(), value.getState());
        } else {
            onStateError(value.getState(), value.getMessage(), value.getData());
        }
    }

    /**
     * 从服务返回的State为1时调用
     *
     * @param data 请求的结果
     */
    abstract protected void onSuccess(T data, String message, int code);


    /**
     * 从服务返回的State不为1时调用
     *
     * @param data    请求的结果
     * @param state   状态码
     * @param message 状态说明
     */
    protected void onStateError(int state, String message, T data) {
    }

    public boolean isShowLoadingDialog() {
        return isShowLoadingDialog;
    }
}
