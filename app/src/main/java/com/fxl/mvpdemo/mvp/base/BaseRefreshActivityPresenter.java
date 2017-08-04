package com.fxl.mvpdemo.mvp.base;

import com.example.mythreeview.EmptyView;

/**
 * Created by Administrator 可爱的路人 on 2017/5/4.
 * 带上拉下拉的Presenter基类
 */

public abstract class BaseRefreshActivityPresenter<T extends IBaseRefreshView> extends RxPresenter<T> {
    public void refreshData(){
        requestData(1, IBaseRefreshView.REFRESH_TYPE_REFRESH);
        mView.setEmptyView(null, EmptyView.TYPE_LOADING);
    }

    public void loadMoreData(int index){
        requestData(index, IBaseRefreshView.REFRESH_TYPE_MORE);
        mView.setEmptyView(null, EmptyView.TYPE_LOADING);
    }

    public void loadData(){
        requestData(1, IBaseRefreshView.REFRESH_TYPE_LOAD);
    }

    public abstract void requestData(int pageIndex, int type);
}
