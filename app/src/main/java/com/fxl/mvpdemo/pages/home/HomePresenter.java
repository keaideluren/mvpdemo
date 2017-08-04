package com.fxl.mvpdemo.pages.home;

import android.content.Context;
import android.content.DialogInterface;

import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUserStatusListener;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.model.business.LoginBusiness;
import com.fxl.mvpdemo.model.event.MessageEvent;
import com.fxl.mvpdemo.mvp.base.RxPresenter;
import com.fxl.mvpdemo.myutils.LogUtil;
import com.fxl.mvpdemo.myutils.SPUtils;

import javax.inject.Inject;

/**
 * Created by Administrator 可爱的路人 on 2017/2/27.
 * 首页
 */

public class HomePresenter extends RxPresenter<HomeContact.HomeView> implements HomeContact.Presenter {
    private Context mContext;

    @Inject
    public HomePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(HomeContact.HomeView view) {
        super.attachView(view);
        TIMManager.getInstance().setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                LogUtil.i(TAG, "receive force offline message");
                mView.showOtherDeviceLoginDialog((dialog, which) -> {
                    if (which== DialogInterface.BUTTON_POSITIVE) {
                        LoginBusiness.loginIm(Constant.ACCOUNT_TYPE, Constant.SDK_APPID
                            , "" + UserInfo.getInstance().getId()
                            , UserInfo.getInstance().getSig(), new TIMCallBack() {
                            @Override
                            public void onError(int i, String s) {
                                mView.showToast(mContext.getString(R.string.login_error));
                                logout();
                                mView.navToLogin();
                            }

                            @Override
                            public void onSuccess() {
                                mView.showToast(mContext.getString(R.string.login_succ));
                                mView.dismissOtherDialog();
                            }
                        });
                    }
                });
            }

            @Override
            public void onUserSigExpired() {
                //票据过期，需要重新登录
                logout();
                mView.navToLogin();
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void stopNetWork() {

    }

    public void logout() {
        //清除本地缓存
        SPUtils.put(mContext, Constant.ShareConstant.FILE_IS_FIRST, Constant.ShareConstant.LAST_USER_ID, 0);
        MessageEvent.getInstance().clear();
        TIMManager.getInstance().logout();
    }
}
