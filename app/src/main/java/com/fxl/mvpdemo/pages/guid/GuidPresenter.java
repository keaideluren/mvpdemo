package com.fxl.mvpdemo.pages.guid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.tencent.TIMCallBack;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.RxBusTags;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.model.business.InitBusiness;
import com.fxl.mvpdemo.model.business.LoginBusiness;
import com.fxl.mvpdemo.model.db.UserDao;
import com.fxl.mvpdemo.model.event.FriendshipEvent;
import com.fxl.mvpdemo.model.event.GroupEvent;
import com.fxl.mvpdemo.model.network.RetrofitDefaultSubscriber;
import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.mvp.base.RxPresenter;
import com.fxl.mvpdemo.myutils.AppUtils;
import com.fxl.mvpdemo.myutils.LogUtil;
import com.fxl.mvpdemo.myutils.SPUtils;
import com.fxl.mvpdemo.myutils.SignUtils;
import com.fxl.mvpdemo.util.JsonParam;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.fxl.mvpdemo.model.Constant.ShareConstant.FILE_IS_FIRST;
import static com.fxl.mvpdemo.model.Constant.ShareConstant.IS_GUIDE_FIRST;
import static com.fxl.mvpdemo.model.Constant.ShareConstant.LAST_USER_ID;
import static com.fxl.mvpdemo.myutils.SPUtils.get;

/**
 * Created by Administrator on 2017/2/15.
 * 系统引导页，Model就是几张图啰，就不单独建立一个Bean了
 */

public class GuidPresenter extends RxPresenter<GuidContact.GudiView> implements GuidContact.Presenter, TIMCallBack, DialogInterface.OnClickListener {
    Context mContext;
    private RetrofitHelper mRetrofitHelper;
    private RetrofitDefaultSubscriber<UserInfo> retrofitDefaultSubscriber;
    private boolean isLoginImSuccess = false;
    private boolean isPermissionGanted = false;
    private boolean isGuidFirst;

    @Inject
    GuidPresenter(RetrofitHelper mRetrofitHelper, Context mContext) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.mContext = mContext;
    }

    @Subscribe(
            tags = {@Tag(RxBusTags.GUID)},
            thread = EventThread.MAIN_THREAD
    )
    public void heardFromGuidAdapter(String v) {
        LogUtil.i(TAG, "Presenter收到了RxBus来的事件");
        SPUtils.put(mContext, FILE_IS_FIRST, IS_GUIDE_FIRST, false);
        mView.navToLogin();
    }

    @Override
    public void attachView(GuidContact.GudiView view) {
        super.attachView(view);
        RxBus.get().register(this);
    }

    @Override
    public void detachView() {
        super.detachView();
        RxBus.get().unregister(this);
    }

    @Override
    public List<Integer> getGuidImages() {
        //SharePreferences中isFirst文件中获得引导页是否已经进入过isGuidFirst
        isGuidFirst = (boolean) get(mContext, FILE_IS_FIRST, IS_GUIDE_FIRST, true);
        List<Integer> guidImageList = new ArrayList<>();
        if (isGuidFirst) {
            //第一次进入应用，背景图案是引导页的图版
            guidImageList.add(R.drawable.bg_guid1);
            guidImageList.add(R.drawable.bg_guid2);
            guidImageList.add(R.drawable.bg_guid3);
            guidImageList.add(R.drawable.bg_guid4);
        } else {
            //非第一次进入应用，启动页,1秒后开始判断是否需要登录，跳转
            guidImageList.add(R.drawable.start_image);
        }
        return guidImageList;
    }

    /**
     * 是否已有用户登录,用到了异步操作，所以不能直接返回结果
     */
    private void isUserLogin() {
        retrofitDefaultSubscriber = new RetrofitDefaultSubscriber<UserInfo>(mView, false) {

            @Override
            public void onError(Throwable e) {
                mView.navToLogin();
            }

            @Override
            protected void onSuccess(UserInfo data, String message, int code) {
                loginSerVerSuccess(data);
            }

            @Override
            protected void onStateError(int state, String message, UserInfo data) {
                mView.navToLogin();
            }
        };
        //sharePreferences里最后一次登录的ID，不为零则从数据库拿用户数据，用户数据不为空且Id不为零不需要重新登录
        Observable.create((ObservableOnSubscribe<Integer>) e -> e.onNext((Integer) SPUtils.get(mContext, FILE_IS_FIRST, LAST_USER_ID, 0)))
                //数据磁盘操作
                .subscribeOn(Schedulers.io())
                //根据最后一次的Id从数据库获得用户数据，Io线程
                .map(lastUserId -> lastUserId == 0 ? new UserInfo() : UserDao.getInstance().findUserByUserId(lastUserId))
                //根据数据库里的数据判断是否需要再次登录,true表示找到了数据大概正确的用户信息
                .map(userByUserId -> userByUserId != null && userByUserId.getId() != 0
                        && !TextUtils.isEmpty(userByUserId.getAutoKey()) ? userByUserId.getAutoKey() : "")
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext((autoKey)->{
//                    //这里也要放到服务里去做，登录工作全后台做
//                    if (!TextUtils.isEmpty(autoKey)) {
//                        loginIm();
//                    } else {
//                        throw new NullPointerException("自动登录Key为空");
//                    }
//                })
                .observeOn(Schedulers.io())
                //执行登录或抛出异常，不能自动登录就去登录页
                .flatMap(autoKey -> {
                    if (!TextUtils.isEmpty(autoKey)) {
                        //这里要放到服务里去做，要不然回调会内存泄漏的
                        return mRetrofitHelper
                                .createApi(GuidContact.Model.class)
                                .loginAutoKey(JsonParam.newInstance()
                                        .add(Constant.AUTO_KEY, autoKey)
                                        .add(Constant.DEVICE_TYPE, "2")
                                        .getMapLoginAndRegister());
                    } else {
                        throw new NullPointerException("自动登录Key为空");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(retrofitDefaultSubscriber);
    }

    /**
     * 登录服务器成功，更新数据库，更新最后一次登录用户Id，单例UserInfo赋值，登录腾讯
     */
    private void loginSerVerSuccess(UserInfo data) {
        UserInfo.setInstance(data);
        SignUtils.TOKEN = UserInfo.getInstance().getToken();
        //这个线程无需回调，跑完就是了
        new Thread(() -> {
            SPUtils.put(mContext, FILE_IS_FIRST, LAST_USER_ID, data.getId());
            UserDao.getInstance().updateOrInsert(data);
        }).run();
        loginIm();
    }

    /**
     * 登录腾讯服务器
     */
    private void loginIm() {
        //递归请求权限
        FriendshipEvent.getInstance().init();
        GroupEvent.getInstance().init();
        InitBusiness.start(mContext);
        LoginBusiness.loginIm(Constant.ACCOUNT_TYPE, Constant.SDK_APPID
                , "" + UserInfo.getInstance().getId()
                , UserInfo.getInstance().getSig(), this);
        Log.i(TAG, "loginIm: 开始登录腾讯");
    }

    /**
     * 请求权限，没有点不再弹出就会再次请求
     */
    private void requestPermission() {
        mView.requestPermission();
    }

    /**
     * 初始化接口签名信息
     */
    private void initSign() {
        AppUtils.deviceName = Build.MANUFACTURER + Build.MODEL;
        try {
            AppUtils.appVersion = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES).versionName;
            AppUtils.appVersionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            AppUtils.imei = tm.getDeviceId();
        } catch (Exception e) {
            AppUtils.imei = "未获得权限";
        }
        AppUtils.sdkVersion = Build.VERSION.RELEASE;
//        SignUtils.TOKEN = UserInfo.getInstance().getToken();
    }

    /**
     * 推送和Im的一些需要登录后初始化的东西
     */
    private void initPush() {

    }

    /**
     * 登录腾讯回调失败
     *
     * @param i 错误码
     * @param s 错误描述
     */
    @Override
    public void onError(int i, String s) {
        LogUtil.i(TAG, "login error : code " + i + " " + s);
        switch (i) {
            case 6208:
                //离线状态下被其他终端踢下线
                LogUtil.i(TAG, "login error : 离线状态下被其他终端踢下线 " + i + " " + s);
                mView.showOtherDeviceLoginDialog(this);
                break;
            default:
                Log.i(TAG, "onError: 登录腾讯失败：" + i + "原因：" + s);
                mView.navToLogin();
                break;
        }
    }

    /**
     * 登录腾讯回调成功
     */
    @Override
    public void onSuccess() {
        initPush();
        isLoginImSuccess = true;
        if (isPermissionGanted) {
            mView.nvaToHome();
        }
        LogUtil.i(TAG, "onSuccess: 登录腾讯成功");
    }

    @Override
    public void onPermissionGanted() {
        isPermissionGanted = true;
        initSign();
        isUserLogin();
        LogUtil.i(TAG, "onSuccess: 获取权限成功");
    }

    /**
     * 被踢下线对话框点击重新登录，确定按钮
     *
     * @param dialog 都知道
     * @param which  都懂
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                loginIm();
                break;
        }
    }

    @Override
    public void stopNetWork() {
        if (retrofitDefaultSubscriber != null) {
            retrofitDefaultSubscriber.unSubscriber();
        }
    }
}
