package com.fxl.mvpdemo.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mythreeview.EmptyView;
import com.fxl.mvpdemo.mylibrary.PTRUi.CustomHeadView;
import com.fxl.mvpdemo.mylibrary.PTRUi.CustomLoadMoreView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator 可爱的路人 on 2017/3/24.
 * 带刷新的基类
 */

public abstract class BaseRefreshFragment<T extends BaseRefreshFragmentPresenter, K> extends BaseFragment<T> implements IBaseRefreshView<K> {
    private BaseQuickAdapter mAdapter;
    protected PtrFrameLayout mPtrFrameLayout;
    private int pageIndex = 1;//我们的服务器后台页码是从1开始的

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPtrLayout();
        initAdapter();
    }

    protected abstract PtrFrameLayout getRefreshView();

    protected abstract BaseQuickAdapter getAdapter();

    protected abstract RecyclerView getRecyclerView();

    @Override
    public void stopRefresh(int state, int type) {
        if (type == IBaseRefreshView.REFRESH_TYPE_LOAD || type == IBaseRefreshView.REFRESH_TYPE_REFRESH) {
            mPtrFrameLayout.refreshComplete();
            mAdapter.setEnableLoadMore(true);
        } else {
            switch (state) {
                case IBaseRefreshView.REFRESH_STATE_SUCCESS:
                    mAdapter.loadMoreComplete();
                    break;
                case IBaseRefreshView.REFRESH_STATE_END:
                    if (pageIndex == 1) {
                        mAdapter.loadMoreEnd(true);
                    } else {
                        mAdapter.loadMoreEnd(false);
                    }
                    pageIndex = pageIndex > 1 ? pageIndex : pageIndex - 1;
                    break;
                case IBaseRefreshView.REFRESH_STATE_ERROR:
                    pageIndex = pageIndex > 1 ? pageIndex : pageIndex - 1;
                    mAdapter.loadMoreFail();
                    break;
            }
        }
    }

    /**
     * 初始化下拉控件
     */
    private void initPtrLayout() {
        mPtrFrameLayout = getRefreshView();
        if (mPtrFrameLayout != null) {
            // the following are default settings
            CustomHeadView customHeadView = new CustomHeadView(mContext);
            mPtrFrameLayout.setHeaderView(customHeadView);
            mPtrFrameLayout.setResistance(1.7f);
            mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
            mPtrFrameLayout.setDurationToClose(200);
            mPtrFrameLayout.setDurationToCloseHeader(500);
// default is false
//            mPtrFrameLayout.setPullToRefresh(false);
// default is true
//            mPtrFrameLayout.setKeepHeaderWhenRefresh(true);
            mPtrFrameLayout.addPtrUIHandler(customHeadView);
            mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    mPresenter.refreshData();
                    pageIndex = 1;
                    mAdapter.setEnableLoadMore(false);
                }

            });
        }
    }

    /**
     * 初始化适配器
     * 上拉在这里
     */
    private void initAdapter() {
        mAdapter = getAdapter();
        RecyclerView recyclerView = getRecyclerView();

        if (mAdapter != null) {
            mAdapter.setEnableLoadMore(false);
            mAdapter.disableLoadMoreIfNotFullPage(recyclerView);
//            mAdapter.setAutoLoadMoreSize(Constant.PAGE_SIZE);//预加载一页
            //设置列表脚
            mAdapter.setLoadMoreView(new CustomLoadMoreView());
            //设置空页面
            emptyView = new EmptyView(getActivity());
            mAdapter.setEmptyView(emptyView);
            mAdapter.setOnLoadMoreListener(() -> {
                pageIndex++;
                mPresenter.loadMoreData(pageIndex);
            }, recyclerView);
        }
    }
}
