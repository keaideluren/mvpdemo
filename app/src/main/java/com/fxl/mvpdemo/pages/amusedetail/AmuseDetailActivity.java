package com.fxl.mvpdemo.pages.amusedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.model.bean.AmuseDetail;
import com.fxl.mvpdemo.mvp.base.BaseActivity;

public class AmuseDetailActivity extends BaseActivity<AmuseDetailPresenter> implements AmuseDetailContact.AmuseDetailView {

    public static void startAmuseDetailActivity(Context context, int amuseId) {
        context.startActivity(new Intent(context,AmuseDetailActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amuse_detail);
    }

    @Override
    public void onReload(View view, int type) {
        mPresenter.requestAmuseDetail(1);
    }

    @Override
    protected void initViewAndData() {
        setEmptyView("约耍圈",0);
        mPresenter.requestAmuseDetail(1);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void setView(AmuseDetail amuseDetail) {
        clearEmptyView();
    }
}
