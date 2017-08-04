package com.fxl.mvpdemo.pages.login;

import android.content.Context;

import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.model.db.UserDao;
import com.fxl.mvpdemo.model.network.RetrofitDefaultSubscriber;
import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.mvp.base.RxPresenter;
import com.fxl.mvpdemo.myutils.LogUtil;
import com.fxl.mvpdemo.myutils.MD5Util;
import com.fxl.mvpdemo.myutils.SPUtils;
import com.fxl.mvpdemo.myutils.SignUtils;
import com.fxl.mvpdemo.util.JsonParam;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.fxl.mvpdemo.model.Constant.ACCOUNT_PWD;
import static com.fxl.mvpdemo.model.Constant.DEVICE_TYPE;

/**
 * Created by Administrator 可爱的路人 on 2017/2/27.
 * 登录界面逻辑
 */

public class LoginPresenter extends RxPresenter<LoginCountact.LoginView> implements LoginCountact.Presenter {

    private RetrofitDefaultSubscriber<UserInfo> apiLoginSubscriber;

    @Inject//注入到LoginActivity里，需要对构造@
    public LoginPresenter() {
    }

    @Inject
    RetrofitHelper mRetrofitHelper;
    @Inject
    Context mContext;

    @Override
    public void doLogin(String phone, String pwd, int loginContactLoginType) {
        stopNetWork();

        apiLoginSubscriber = new RetrofitDefaultSubscriber<UserInfo>(mView) {
            @Override
            protected void onSuccess(UserInfo data, String message, int code) {
                //用户数据放数据库
                LogUtil.i(TAG, "onSuccess: 存数据库" + data);
                SPUtils.put(mContext, Constant.ShareConstant.FILE_IS_FIRST, Constant.ShareConstant.LAST_USER_ID, data.getId());
                UserDao.getInstance().updateOrInsert(data);
                SignUtils.TOKEN = data.getToken();
                mView.navToHome();
            }
        };

        mRetrofitHelper.createApi(LoginCountact.Model.class).loginByPassWord(JsonParam.newInstance()
            .add(Constant.PHONE, phone)
            .add(ACCOUNT_PWD, MD5Util.string2MD5(pwd).toLowerCase())
            .add(DEVICE_TYPE, 2)//1苹果 2安卓
            .getMapLoginAndRegister())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(userInfo -> {
                UserInfo.setInstance(userInfo.getData());
                //去登录，startactivity
                return userInfo;
            })
            .observeOn(Schedulers.io())
            .subscribe(apiLoginSubscriber);

    }

    @Override
    public void stopNetWork() {
        if (apiLoginSubscriber != null) {
            apiLoginSubscriber.unSubscriber();
        }
    }
}
