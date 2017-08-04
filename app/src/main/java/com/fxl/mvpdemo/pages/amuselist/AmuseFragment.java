package com.fxl.mvpdemo.pages.amuselist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mythreeview.EmptyView;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.model.bean.Amuse;
import com.fxl.mvpdemo.mvp.base.BaseRefreshFragment;

import java.util.concurrent.TimeUnit;

import in.srain.cube.views.ptr.PtrFrameLayout;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * create an instance of this fragment.
 * 约耍圈列表
 */
public class AmuseFragment extends BaseRefreshFragment<AmuseListPresenter, Amuse> implements AmuseListContact.AmuseView {
    private ViewGroup container;

    public AmuseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container = container;

        return inflater.inflate(R.layout.fragment_amuse, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyView = new EmptyView(getActivity(), "约耍");
        emptyView.setOnReloadListener((view1, type) -> Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> setEmptyError(EmptyView.TYPE_NO_DATA, "再来一次")));
//        container.addView(emptyView);
    }

    //这里调用应
    private void setEmptyError(int type, String message) {
        emptyView.setEmptyText(message, type);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_amuse;
    }

    @Override
    protected PtrFrameLayout getRefreshView() {
        return null;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return null;
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return null;
    }

    @Override
    public int onLoadMore(Amuse baseData, String message, int code) {
        return 0;
    }

    @Override
    public boolean onLoad(Amuse baseData, String message, int code) {
//        container.removeView(emptyView);
        return false;
    }

    @Override
    public boolean onRefresh(Amuse baseData, String message, int code) {
        return false;
    }
}
