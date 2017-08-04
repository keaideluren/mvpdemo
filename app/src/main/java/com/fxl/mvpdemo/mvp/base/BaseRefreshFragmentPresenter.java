package com.fxl.mvpdemo.mvp.base;

import com.example.mythreeview.EmptyView;

/**
 * Created by Administrator 可爱的路人 on 2017/5/4.
 * 上下拉Presenter
 */

public abstract class BaseRefreshFragmentPresenter<T extends IBaseRefreshView> extends BaseFragmentPresenter<T> {

    @Override
    public void lazyLoad() {
        requestData(1, IBaseRefreshView.REFRESH_TYPE_LOAD);
    }

    public void refreshData(){
        requestData(1, IBaseRefreshView.REFRESH_TYPE_REFRESH);
        mView.setEmptyView(null, EmptyView.TYPE_LOADING);
    }

    public void loadMoreData(int index){
        requestData(index, IBaseRefreshView.REFRESH_TYPE_MORE);
        mView.setEmptyView(null, EmptyView.TYPE_LOADING);
    }

    public abstract void requestData(int pageIndex, int type);
}
