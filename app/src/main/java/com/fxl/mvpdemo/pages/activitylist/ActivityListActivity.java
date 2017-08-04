package com.fxl.mvpdemo.pages.activitylist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.model.bean.ActivityBean;
import com.fxl.mvpdemo.mvp.base.BaseRefreshActivity;
import com.fxl.mvpdemo.mvp.base.IBaseRefreshView;
import com.fxl.mvpdemo.util.ImageLoaderUtil;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class ActivityListActivity extends BaseRefreshActivity<ActivityListPresenter, List<ActivityBean>> implements ActivityListContact.AcListView {

    @BindView(R.id.rv_activity_list)
    RecyclerView rvActivityList;
    @BindView(R.id.refresh_activity_list)
    PtrFrameLayout refreshActivityList;
    private BaseQuickAdapter<ActivityBean, BaseViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected PtrFrameLayout getRefreshView() {
        return refreshActivityList;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        mAdapter = new BaseQuickAdapter<ActivityBean, BaseViewHolder>(R.layout.item_activity_list) {
            @Override
            protected void convert(BaseViewHolder helper, ActivityBean item) {
                helper.setText(R.id.tv_item_activity_name, item.getBusinessName());
                helper.setText(R.id.tv_item_activity_description, item.getTitle());
                ImageLoaderUtil.setPic(mContext
                        , item.getCover()
                        , helper.getView(R.id.civ_item_activity_shadow));
            }
        };
        rvActivityList.setLayoutManager(new LinearLayoutManager(mContext));
        rvActivityList.setAdapter(mAdapter);
        return mAdapter;
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return rvActivityList;
    }

    @Override
    public int onLoadMore(List<ActivityBean> baseData, String message, int code) {
        if (baseData == null && baseData.size() > 0) {
            if (baseData.size() > 0) {
                mAdapter.addData(baseData);
                return IBaseRefreshView.REFRESH_STATE_SUCCESS;
            }
            return IBaseRefreshView.REFRESH_STATE_END;
        }
        return IBaseRefreshView.REFRESH_STATE_ERROR;
    }

    @Override
    public boolean onLoad(List<ActivityBean> baseData, String message, int code) {
        if (baseData != null && code == 1) {
            mAdapter.setNewData(baseData);
            return true;
        }
        return false;
    }

    @Override
    public boolean onRefresh(List<ActivityBean> baseData, String message, int code) {
        return onLoad(baseData, message, code);
    }
}
