package com.fxl.mvpdemo.pages.guid;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.tencent.TIMCallBack;
import com.tencent.TIMFriendGenderType;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMManager;
import com.tencent.TIMOfflinePushSettings;
import com.fxl.mvpdemo.LeplusApplication;
import com.fxl.mvpdemo.model.Constant;
import com.fxl.mvpdemo.model.bean.UserInfo;
import com.fxl.mvpdemo.model.network.RetrofitHelper;
import com.fxl.mvpdemo.mvp.component.DaggerServiceComponent;
import com.fxl.mvpdemo.myutils.SignUtils;
import com.fxl.mvpdemo.util.JsonParam;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;


/**
 * 自动登录
 */
public class AutoLoginService extends IntentService implements TIMCallBack {

    public static final String LOGIN_AUTO_KEY = "com.xunlebao.leplus.service.extra.autokey";
    private final Handler handlerMain;
    private int loginImCount;
    @Inject
    RetrofitHelper retrofitHelper;

    @Inject
    public AutoLoginService() {
        super("AutoLoginService");
        handlerMain = new Handler(LeplusApplication.getInstance().getMainLooper());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //注入AppComponent，利用AppComponent注入retrofitHelper实例
        DaggerServiceComponent.builder().appComponent(LeplusApplication.getAppComponent())
                .build().inject(this);
        if (intent != null) {
            String autoKey = intent.getStringExtra(LOGIN_AUTO_KEY);
            SignUtils.TOKEN = UserInfo.getInstance().getToken();
            autoLogin(autoKey);
            handlerMain.post(this::navToHome);
        }
    }

    private void autoLogin(String autoKey) {
        JSONObject user = new JSONObject();
        // 第一个键phone的值是数组，所以需要创建数组对象
        try {
            user.put("AutoKey", autoKey);
            user.put("deviceType", 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            retrofitHelper
                    .createApi(GuidContact.Model.class)
                    .loginAutoKey(JsonParam.newInstance()
                            .add(Constant.AUTO_KEY, autoKey)
                            .add(Constant.DEVICE_TYPE, "2")
                            .getMapLoginAndRegister()).subscribe();
//            Response response = OkHttpManager.getInstance().postExecute(Contants.USER_AUTO_LOGIN, user.toString());
//            if (response != null && response.isSuccessful()) {
//                String string = response.body().string();
//                BaseRespObject<UserInfo> o = new Gson().fromJson(string, new TypeToken<BaseRespObject<UserInfo>>() {
//                }.getType());
//                if (o.getState() == 1) {
//                    UserInfo.setInstance(o.getData());
//                    UserInfo.getInstance().writeToCache(getApplicationContext());
//                    JsonParam.setTOKEN(UserInfo.getInstance().getToken());
//                    setLastSplashPic();
//                } else {
//                    UserInfo.getInstance().setId(0);
//                    UserInfo.getInstance().writeToCache(getApplicationContext());
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得最新的启动页图片
     */
    private void setLastSplashPic() {
//        if (UserInfo.getInstance().getStartPage() != null) {
//            final String coverUrl = UserInfo.getInstance().getStartPage().getCoverUrl();
//            final String jumpUrl = UserInfo.getInstance().getStartPage().getJumpUrl();
//            final int jumpType = UserInfo.getInstance().getStartPage().getJumpType();
////            final String coverUrl = "http://opqgl0a7u.bkt.clouddn.com/image/1003/1497423029822.jpg";
//            if (TextUtils.isEmpty(coverUrl)) {
//                SharedPreferences sp = getSharedPreferences("isFirst", MODE_PRIVATE);
//                sp.edit().putString("splashPicUrl", "").apply();
//            } else {
//                final SharedPreferences sp = getSharedPreferences("isFirst", MODE_PRIVATE);
//                String splashPicUrl = sp.getString("splashPicUrl", "");
//                if (!splashPicUrl.equals(coverUrl)) {
//                    LogUtils.i("AutoLoginService", "启动图片加载前：" + coverUrl);
//                    try {
//                        handlerMain.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Glide.with(AutoLoginService.this).load(coverUrl)
//                                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                                        .into(new SimpleTarget<GlideDrawable>() {
//                                            @Override
//                                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                                                LogUtils.i("AutoLoginService", "启动图片加载完成：" + coverUrl);
//                                                sp.edit().putString("splashPicUrl", coverUrl)
//                                                        .putString("jumpUrl", jumpUrl)
//                                                        .putInt("jumpType", jumpType)
//                                                        .apply();
//                                            }
//                                        });
//                            }
//                        });
//                    } catch (Exception e) {
//                        LogUtils.i("AutoLoginService", "启动图片加载错误：" + e.getMessage());
//                    }
//                }
//            }
//        }
    }


    /**
     * imsdk登录失败后回调
     */
    @Override
    public void onError(int i, String s) {
//        switch (i) {
//            case 6208:
//                //离线状态下被其他终端踢下线
//                LogUtils.e("splash login", "login error : 离线状态下被其他终端踢下线 " + i + " " + s);
//                NotifyDialog dialog = new NotifyDialog();
//                navToHome();
////                dialog.show(getString(R.string.kick_logout), getSupportFragmentManager(), new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        navToHome();
////                    }
////                });
//                break;
//            default:
////                Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
//                navToHome();
//                break;
//        }

    }

    public void navToHome() {
        //登录之前要初始化群和好友关系链缓存
        //群和好友关系存储不需要
//        if (loginImCount <= 2) {
//            loginImCount++;
//            FriendshipEvent.getInstance().init();
//            GroupEvent.getInstance().init();
//
//            LoginBusiness.loginIm(UserInfo.getInstance().getId(), UserInfo.getInstance().getSig(), this);
//        }
    }

    /**
     * imsdk登录成功后回调
     */
    @Override
    public void onSuccess() {
        TIMCallBack timCallBack = new TIMCallBack() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess() {

            }
        };
        if (UserInfo.getInstance().getGender() == 0) {
            //男
            TIMFriendshipManager.getInstance().setGender(TIMFriendGenderType.Male, timCallBack);
        } else {
            TIMFriendshipManager.getInstance().setGender(TIMFriendGenderType.Female, timCallBack);
        }
        TIMFriendshipManager.getInstance().setSelfSignature(UserInfo.getInstance().getSignature(), timCallBack);
        //离线推送设置
        TIMOfflinePushSettings settings = new TIMOfflinePushSettings();
        //开启离线推送
        settings.setEnabled(true);
        TIMManager.getInstance().configOfflinePushSettings(settings);

        //初始化程序后台后消息推送
//        PushUtil.getInstance();
//        //初始化消息监听
//        MessageEvent.getInstance();
////        推送
//        initUmeng();
////        注册小米化为推送
//        MobclickAgent.onProfileSignIn(UserInfo.getInstance().getId());
//        String deviceMan = Build.MANUFACTURER + Build.MODEL;
//        LogUtils.e("手机型号", deviceMan);
//        if (new DevicesUtil().isMIUI()) {
//            LogUtils.e("推送", "小米");
//            if (shouldMiInit()) {
//                MiPushClient.registerPush(getApplicationContext(), "2882303761517511640", "5561751111640");
//            }
//        }
//        if (deviceMan.toLowerCase().contains("huawei")) {
//            LogUtils.e("推送", "华为");
//            PushManager.requestToken(this);
//        }
//
//        try {
//            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//            JsonParam.setAppVerion(versionName);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    private void initUmeng() {
        // 友盟推送
//        if (!new DevicesUtil().isMIUI()) {
//            final PushAgent mPushAgent = PushAgent.getInstance(this);
////注册推送服务，每次调用register方法都会回调该接口
//            mPushAgent.addAlias(UserInfo.getInstance().getId(), XUNLEBAO_PUSH_ALIAS_TYPE, new UTrack.ICallBack() {
//                @Override
//                public void onMessage(boolean b, String s) {
//                    LogUtils.i("UmengPush", "注册别名:" + s);
//                }
//            });
//        }
    }

    /**
     * 判断小米推送是否已经初始化
     */
    private boolean shouldMiInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
