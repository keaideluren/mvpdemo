package com.fxl.mvpdemo.mvp.base;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mythreeview.EmptyView;
import com.fxl.mvpdemo.mylibrary.PTRUi.CustomHeadView;
import com.fxl.mvpdemo.mylibrary.PTRUi.CustomLoadMoreView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator 可爱的路人 on 2017/3/23.
 * 带刷新，加载的基类
 */

public abstract class BaseRefreshActivity<T extends BaseRefreshActivityPresenter, K> extends BaseActivity<T> implements IBaseRefreshView<K> {

    private BaseQuickAdapter mAdapter;
    private PtrFrameLayout mPtrFrameLayout;
    private int pageIndex = 1;//我们的服务器后台页码是从1开始的

    @Override
    protected void initViewAndData() {
        initAdapter();
        initPtrLayout();
        mPresenter.loadData();
    }

    protected abstract PtrFrameLayout getRefreshView();

    protected abstract BaseQuickAdapter getAdapter();

    protected abstract RecyclerView getRecyclerView();

    /**
     * @param type  是下拉还是上拉0下拉，1上拉，分别要形成互斥
     * @param state 状态0，上拉成功，1上拉无更多数据，2上拉失败
     */
    @Override
    public void stopRefresh(int state, int type) {
        if (type == IBaseRefreshView.REFRESH_TYPE_REFRESH || type == IBaseRefreshView.REFRESH_TYPE_LOAD) {
            if (mPtrFrameLayout != null) {
                mPtrFrameLayout.refreshComplete();
            }
            mAdapter.setEnableLoadMore(true);
        } else {
            switch (state) {
                case IBaseRefreshView.REFRESH_STATE_SUCCESS:
                    mAdapter.loadMoreComplete();
                    break;
                case IBaseRefreshView.REFRESH_STATE_END:
                    mAdapter.loadMoreEnd();
                    pageIndex = pageIndex > 1 ? pageIndex - 1 : pageIndex;
                    break;
                case IBaseRefreshView.REFRESH_STATE_ERROR:
                    pageIndex = pageIndex > 1 ? pageIndex - 1 : pageIndex;
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
     */
    private void initAdapter() {
        mAdapter = getAdapter();
        RecyclerView recyclerView = getRecyclerView();
        if (mAdapter != null) {
            mAdapter.setEnableLoadMore(true);
            mAdapter.disableLoadMoreIfNotFullPage(recyclerView);
//            mAdapter.setAutoLoadMoreSize(Constant.PAGE_SIZE);//自动刷新
            //设置列表脚
            mAdapter.setLoadMoreView(new CustomLoadMoreView());
            emptyView = new EmptyView(mContext);
            mAdapter.setEmptyView(emptyView);
            mAdapter.setOnLoadMoreListener(() -> {
                pageIndex++;
                mPresenter.loadMoreData(pageIndex);
            }, recyclerView);
        }
    }
}
