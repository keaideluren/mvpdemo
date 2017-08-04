package com.fxl.mvpdemo;


import android.support.multidex.MultiDexApplication;

import com.tencent.TIMManager;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.fxl.imsdk.Foreground;
import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.business.InitBusiness;
import com.fxl.mvpdemo.mvp.component.AppComponent;
import com.fxl.mvpdemo.mvp.component.DaggerAppComponent;
import com.fxl.mvpdemo.mvp.module.AppModule;
import com.fxl.mvpdemo.myutils.LogUtil;

/**
 * Created by Administrator on 2017/2/4.
 * 工程
 */

public class LeplusApplication extends MultiDexApplication {
    private static LeplusApplication instance;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtil.mDebuggable = Constant.IS_DEBUG ? 6 : 6;

        InitBusiness.start(getApplicationContext(), 0);
        Foreground.init(this);
        if (MsfSdkUtils.isMainProcess(this)) {
            TIMManager.getInstance().setOfflinePushListener(notification -> notification.doNotify(getApplicationContext(), R.drawable.ic_launcher));
        }
        TIMManager.getInstance().setLogPrintEanble(false);
    }

    public static LeplusApplication getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getInstance()))
                .build();
        }
        return appComponent;
    }
}
