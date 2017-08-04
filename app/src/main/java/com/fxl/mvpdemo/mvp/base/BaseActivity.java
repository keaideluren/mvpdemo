package com.fxl.mvpdemo.mvp.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mythreeview.EmptyView;
import com.example.mythreeview.loading.AVLoadingIndicatorDialog;
import com.fxl.mvpdemo.LeplusApplication;
import com.fxl.mvpdemo.mvp.MVP;
import com.fxl.mvpdemo.mvp.component.ActivityComponent;
import com.fxl.mvpdemo.mvp.component.DaggerActivityComponent;
import com.fxl.mvpdemo.mvp.module.ActivityModule;
import com.fxl.mvpdemo.myutils.CustomToast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by codeest on 2016/8/2.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends RxPresenter> extends AppCompatActivity implements MVP.BaseView
        , DialogInterface.OnDismissListener, EmptyView.OnReloadListener {
    protected Unbinder bind;
    private AVLoadingIndicatorDialog loadingDialog;
    @Inject
    protected T mPresenter;
    protected Activity mContext;
    protected String TAG;
    protected EmptyView emptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mContext = this;
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        bind = ButterKnife.bind(this);
        initViewAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showToast(String message) {
        CustomToast.show(mContext, message, CustomToast.LENGTH_SHORT);
    }

    /**
     * 设置空界面，默认界面，加载中的前置界面，
     * emptyview为空就new出来再添加
     * 不为空就设置EmptyView的状态
     * @param title
     */
    public void setEmptyView(String title, int type) {
        if (emptyView == null) {
            FrameLayout rootLayout = (FrameLayout) findViewById(android.R.id.content);
            if (title == null) {
                emptyView = new EmptyView(this);
            } else {
                emptyView = new EmptyView(this, title);
            }
            emptyView.setOnReloadListener(this);
            rootLayout.addView(emptyView);
        } else {
            emptyView.setEmptyText(title, type);
        }
    }

    @Override
    public void clearEmptyView() {
        if (emptyView != null) {
            FrameLayout rootLayout = (FrameLayout) findViewById(android.R.id.content);
            rootLayout.removeView(emptyView);
        }
    }

    @Override
    public void onReload(View view, int type) {
        //todo 数据加载失败，需要重新请求网络或其它耗时操作
    }

    @Override
    public void showBaseDialog(int type) {
        if (loadingDialog == null) {
            loadingDialog = new AVLoadingIndicatorDialog(mContext);
            loadingDialog.setOnDismissListener(this);
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void dismissBaseDialog(int type) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(LeplusApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (bind != null) {
            bind.unbind();
        }
    }

    protected abstract void initViewAndData();

    protected abstract void initInject();

    @Override
    public void onDismiss(DialogInterface dialog) {
        mPresenter.stopNetWork();
    }
}