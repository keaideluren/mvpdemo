package com.fxl.mvpdemo.pages.home;

import android.content.DialogInterface;

import com.fxl.mvpdemo.mvp.MVP;

/**
 * Created by Administrator 可爱的路人 on 2017/2/27.
 * 主界面的VP联系
 */

public interface HomeContact {
    interface HomeView extends MVP.BaseView {
        /**
         * 显示被踢下线对话框
         * @param callback 用户点击重新登录还是或是退出的回调
         */
        void showOtherDeviceLoginDialog(DialogInterface.OnClickListener callback);

        void navToLogin();

        void dismissOtherDialog();
    }
    interface Presenter extends MVP.BasePresenter<HomeView> {


    }
}
