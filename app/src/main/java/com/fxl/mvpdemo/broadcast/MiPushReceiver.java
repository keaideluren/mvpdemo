package com.fxl.mvpdemo.broadcast;

/**
 * Created by Administrator on 2016/10/20.
 *
 */
public class MiPushReceiver /*extends PushMessageReceiver*/ {
    private final String TAG = "MiPushMessageReceiver";
    private String mRegId;
    private int mBussId = 199;
    private int mBussIdDebug = 278;

//    @Override
//    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
//        LogUtils.d(TAG,"onNotificationMessageClicked is called. " + message.toString());
//        LogUtils.d(TAG, getSimpleDate() + " " + message.getContent());
//    }
//
//    @Override
//    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
//        LogUtils.d(TAG,"onNotificationMessageArrived is called. " + message.toString());
//        LogUtils.d(TAG, getSimpleDate() + " " + message.getContent());
//    }
//
//    @Override
//    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
//        LogUtils.d(TAG, "onReceiveRegisterResult is called. " + message.toString());
//        String command = message.getCommand();
//        List<String> arguments = message.getCommandArguments();
//        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
//
//        LogUtils.d(TAG, "cmd: " + command + " | arg: " + cmdArg1
//                + " | result: " + message.getResultCode() + " | reason: " + message.getReason());
//
//        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
//            if (message.getResultCode() == ErrorCode.SUCCESS) {
//                LogUtils.i("推送", "绑定小米推送");
//                mRegId = cmdArg1;
//
//                TIMOfflinePushToken param = new TIMOfflinePushToken();
//                param.setToken(mRegId);
//                if (Constant.IS_DEBUG) {
//                    param.setBussid(mBussIdDebug);
//                    LogUtils.d("MiPushReceiver", "TokeyDebug");
//                } else {
//                    param.setBussid(mBussId);
//                    LogUtils.d("MiPushReceiver", "TokeyRelease");
//                }
//                TIMManager.getInstance().setOfflinePushToken(param, new TIMCallBack() {
//                    @Override
//                    public void onError(int i, String s) {
//                        LogUtils.d("MiPushReceiver", s+i);
//                    }
//
//                    @Override
//                    public void onSuccess() {
//                        LogUtils.d("MiPushReceiver", "set_miPushToken_success");
//                    }
//                });
//            }
//        }
//
//        LogUtils.d(TAG, "regId: " + mRegId);
//    }
//
//    @SuppressLint("SimpleDateFormat")
//    private static String getSimpleDate() {
//        return new SimpleDateFormat("MM-dd hh:mm:ss").format(new Date());
//    }
}
