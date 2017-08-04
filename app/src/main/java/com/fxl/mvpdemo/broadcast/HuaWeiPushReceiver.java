package com.fxl.mvpdemo.broadcast;

//import com.huawei.android.pushagent.PushReceiver;
//import com.huawei.android.pushagent.api.PushEventReceiver;

/**
 * Created by Administrator on 2016/10/20.
 * 华为推送
 */
public class HuaWeiPushReceiver /*extends PushEventReceiver*/ {
    private final String TAG = "HwPushMessageReceiver";
    private String mToken = "";
    private int mBussId = 226;
    private int mBussIdDebug = 279;

//    @Override
//    public void onToken(Context context, String token, Bundle extras){
//        String belongId = extras.getString("belongId");
//        String content = "获取token和belongId成功，token = " + token + ",belongId = " + belongId;
//
//        mToken = token;
//        LogUtils.e(TAG, content);
//
//        TIMOfflinePushToken param = new TIMOfflinePushToken();
//        param.setToken(token);
//        if (Constant.IS_DEBUG) {
//            param.setBussid(mBussIdDebug);
//            LogUtils.d("HuaWeiPushReceiver", "Debug");
//        } else {
//            param.setBussid(mBussId);
//            LogUtils.d("HuaWeiPushReceiver", "Release");
//        }
//        TIMManager.getInstance().setOfflinePushToken(param);
//    }
//
//    @Override
//    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
//        try {
//            String content = "收到一条Push消息： " + new String(msg, "UTF-8");
//            LogUtils.e(TAG, content);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public void onEvent(Context context, PushReceiver.Event event, Bundle extras) {
//        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
//            int notifyId = extras.getInt(PushReceiver.BOUND_KEY.pushNotifyId, 0);
//            if (0 != notifyId) {
//                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//                manager.cancel(notifyId);
//            }
//            String content = "收到通知附加消息： " + extras.getString(PushReceiver.BOUND_KEY.pushMsgKey);
//            LogUtils.e(TAG, content);
//        }
//    }
}
