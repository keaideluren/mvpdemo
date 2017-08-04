package com.fxl.mvpdemo.model.network;

import com.example.mythreeview.EmptyView;
import com.fxl.mvpdemo.mvp.base.IBaseRefreshView;
import com.fxl.mvpdemo.myutils.LogUtil;

/**
 * Created by Administrator 可爱的路人 on 2017/5/8.
 * 默认的实现列表回调
 */

public class RetrofitRefreshSubscriber<D> extends RetrofitDefaultSubscriber<D> {
    private IBaseRefreshView<D> refreshView;
    private int type;

//    /**
//     * @param refreshView
//     * @param type        0刷新第一次，1加载更多
//     */
//    public RetrofitRefreshSubscriber(BaseRefreshActivity refreshView, int type) {
//        super(refreshView, false);
//        this.refreshView = refreshView;
//        this.type = type;
//    }

    /**
     * @param refreshView
     * @param type        0刷新第一次，1加载更多
     */
    public RetrofitRefreshSubscriber(IBaseRefreshView refreshView, int type) {
        super(refreshView, false);
        this.refreshView = refreshView;
        this.type = type;
    }

    @Override
    protected void onSuccess(D data, String message, int code) {
        switch (type) {
            case IBaseRefreshView.REFRESH_TYPE_LOAD:
                refreshView.stopRefresh(0, type);
                if (!refreshView.onLoad(data, message, code)) {
                    refreshView.setEmptyView(message, EmptyView.TYPE_NO_DATA);
                }
                break;
            case IBaseRefreshView.REFRESH_TYPE_REFRESH:
                refreshView.stopRefresh(0, type);
                if (!refreshView.onRefresh(data, message, code)) {
                    refreshView.setEmptyView(message, EmptyView.TYPE_NO_DATA);
                }
                break;
            case IBaseRefreshView.REFRESH_TYPE_MORE:
                int state = refreshView.onLoadMore(data, message, code);
                refreshView.stopRefresh(state, type);
                break;
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("httpretrofit", "onError:错误：failer " + e.getMessage());
        refreshView.stopRefresh(IBaseRefreshView.REFRESH_STATE_ERROR, type);
        refreshView.setEmptyView(null, EmptyView.TYPE_ERROR);
    }

    @Override
    protected void onStateError(int state, String message, D data) {
        if (type == IBaseRefreshView.REFRESH_TYPE_LOAD || type == IBaseRefreshView.REFRESH_TYPE_REFRESH) {
            refreshView.setEmptyView(message, EmptyView.TYPE_NO_DATA);
        }
        refreshView.stopRefresh(IBaseRefreshView.REFRESH_STATE_END, type);
    }
}
