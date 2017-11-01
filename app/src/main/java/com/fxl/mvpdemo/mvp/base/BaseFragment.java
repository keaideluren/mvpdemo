package com.fxl.mvpdemo.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mythreeview.EmptyView;
import com.example.mythreeview.loading.AVLoadingIndicatorDialog;
import com.fxl.mvpdemo.LeplusApplication;
import com.fxl.mvpdemo.mvp.FragmentScope;
import com.fxl.mvpdemo.mvp.MVP;
import com.fxl.mvpdemo.mvp.component.DaggerFragmentComponent;
import com.fxl.mvpdemo.mvp.component.FragmentComponent;
import com.fxl.mvpdemo.mvp.module.FragmentModule;
import com.fxl.mvpdemo.myutils.CustomToast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by codeest on 2016/8/2.
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BaseFragmentPresenter> extends Fragment implements MVP.BaseView, DialogInterface.OnDismissListener, EmptyView.OnReloadListener {

    @Inject
    protected T mPresenter;
    @FragmentScope
    @Inject
    protected  Activity mActivity1;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected boolean isInited = false;
    protected boolean isFirst = true;
    protected boolean isVisible = false;
    protected Unbinder mUnBinder;
    private AVLoadingIndicatorDialog loadingDialog;
    protected EmptyView emptyView;
    private ViewGroup container;

    @Override
    public void onAttach(Context context) {
        mActivity = getActivity();
        mContext = context;
        super.onAttach(context);
    }

    /**
     * 使用 ViewPager来操作
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        if (isVisibleToUser && isFirst && isInited) {
            isFirst = false;
            if (mPresenter != null) {
                mPresenter.lazyLoad();
            }
        }
    }

    /**
     * 使用 FragmentManager来操作
     * 使用Hide show来操作
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.isVisible = !hidden;
        if (!hidden && isFirst && isInited) {
            isFirst = false;
            if (mPresenter != null) {
                mPresenter.lazyLoad();
            }
        }
    }

    /**
     * 其它方法
     * 在布局中直接引用,或用户按返回键，或跳转过来
     */
    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(LeplusApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void showToast(String messsage) {
        CustomToast.show(getContext(), messsage, CustomToast.LENGTH_SHORT);
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

    @Override
    public void onDismiss(DialogInterface dialog) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        this.container = container;
        mUnBinder = ButterKnife.bind(this, mView);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        return mView;
    }

    /**
     * 设置空界面，默认界面，加载中的前置界面，
     *
     * @param title
     */
    @Override
    public void setEmptyView(String title, int type) {
        if (container == null) {
            return;
        }
        if (emptyView == null) {
            if (title == null) {
                emptyView = new EmptyView(getActivity());
            } else {
                emptyView = new EmptyView(getActivity(), title);
            }
            emptyView.setOnReloadListener(this);
            container.addView(emptyView);
        } else {
            emptyView.setEmptyText(title, type);
        }
    }

    @Override
    public void clearEmptyView() {
        if (emptyView != null) {
            container.removeView(emptyView);
        }
    }

    @Override
    public void onReload(View view, int type) {
        //todo 数据加载失败，需要重新请求网络或其它耗时操作
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        isInited = true;
        if (isFirst && isVisible) {
            isFirst = false;
            if (mPresenter != null) {
                mPresenter.lazyLoad();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detachView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    protected abstract void initInject();

    protected abstract int getLayoutId();
}