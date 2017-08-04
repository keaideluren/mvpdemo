package com.fxl.mvpdemo.wxapi;

import android.app.Activity;

public class WXPayEntryActivity extends Activity /*implements IWXAPIEventHandler*/ {

//    private IWXAPI api;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        api = WXAPIFactory.createWXAPI(this, "wxd9c9ad12c7831f3b");
//        api.handleIntent(getIntent(), this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        finish();
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        api.handleIntent(intent, this);
//    }
//
//    @Override
//    public void onReq(BaseReq req) {
//        Toast.makeText(getApplicationContext(),"onReq",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onResp(BaseResp resp) {
//
//        int code = resp.errCode;
//
//        if (code == 0){
//            //显示充值成功的页面和需要的操作
//            sendOrderedBroadcast(new Intent(Constant.INTENT_WECHAT_SUCCESS).putExtra("type",0),null);
//        }
//
//        if (code == -1){
//            //错误
//            sendOrderedBroadcast(new Intent(Constant.INTENT_WECHAT_SUCCESS).putExtra("type",-1),null);
//
//        }
//
//        if (code == -2){
//            sendOrderedBroadcast(new Intent(Constant.INTENT_WECHAT_SUCCESS).putExtra("type",-2),null);
//            //用户取消
//        }
//    }
}