package com.fxl.mvpdemo.pages.amusedetail;

import com.example.mythreeview.EmptyView;
import com.fxl.mvpdemo.model.bean.AmuseDetail;
import com.fxl.mvpdemo.model.network.RetrofitDefaultSubscriber;
import com.fxl.mvpdemo.mvp.base.RxPresenter;

import java.util.LinkedList;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/6/23.
 */

public class AmuseDetailPresenter extends RxPresenter<AmuseDetailContact.AmuseDetailView> implements AmuseDetailContact.Presenter {
    private LinkedList<RetrofitDefaultSubscriber> subscribers = new LinkedList<>();
    AmuseDetailModel mModel;

    @Inject
    public AmuseDetailPresenter(AmuseDetailModel mModel) {
        this.mModel = mModel;
    }

    @Override
    public void stopNetWork() {
        for (RetrofitDefaultSubscriber r : subscribers) {
            r.unSubscriber();
        }
    }

    @Override
    public void attachView(AmuseDetailContact.AmuseDetailView view) {
        super.attachView(view);
    }

    @Override
    public void requestAmuseDetail(int amuseId) {
        mModel.getAmuseDetail(715)
                .subscribe(new RetrofitDefaultSubscriber<AmuseDetail>(mView, false) {
                    @Override
                    protected void onSuccess(AmuseDetail data, String message, int code) {
                        subscribers.remove(this);
                        mView.setView(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        subscribers.remove(this);
                        mView.setEmptyView(null, EmptyView.TYPE_ERROR);
                    }

                    @Override
                    protected void onStateError(int state, String message, AmuseDetail data) {
                        super.onStateError(state, message, data);
                        subscribers.remove(this);
                        mView.setEmptyView(message, EmptyView.TYPE_NO_DATA);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        subscribers.add(this);
                    }
                });
    }
}
