package com.fxl.mvpdemo.myutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2017/2/15.
 * 应用相关工具类
 */

public class AppUtils {
    public static String appVersion;
    public static int appVersionCode;
    public static String deviceName;
    public static String imei;
    public static String sdkVersion;
    private static AppUtils instance;

    private AppUtils() {
        /* cannot be instantiated */
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final byte[] lock = new byte[0];
    public static AppUtils getInstance(){
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new AppUtils();
                }
            }
        }
        return instance;
    }
}
