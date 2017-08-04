package com.fxl.mvpdemo.pages.guid;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.dialog.NotifyDialog;
import com.fxl.mvpdemo.mvp.base.BaseActivity;
import com.fxl.mvpdemo.myutils.LogUtil;
import com.fxl.mvpdemo.pages.home.HomeActivity;
import com.fxl.mvpdemo.pages.login.LoginActivity;
import com.fxl.mvpdemo.util.RxPermissionUtil;

import butterknife.BindView;

public class GuidActivity extends BaseActivity<GuidPresenter> implements GuidContact.GudiView {

    @BindView(R.id.main_content)
    ViewPager mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_guid);
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestPermission();
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void initViewAndData() {
        mainContent.setAdapter(new GuidAdapter(mContext, mPresenter.getGuidImages()));
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void navToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void nvaToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void showOtherDeviceLoginDialog(DialogInterface.OnClickListener onClickListener) {
        NotifyDialog dialog = new NotifyDialog();
        dialog.show(getString(R.string.kick_logout), getSupportFragmentManager(), onClickListener);
    }

    @Override
    public void requestPermission() {
//        RxPermissionUtil.requestPermission(this
//                , () -> mPresenter.onPermissionGanted()
//                , Manifest.permission.READ_PHONE_STATE
//                , Manifest.permission.WRITE_EXTERNAL_STORAGE
//                , Manifest.permission.READ_EXTERNAL_STORAGE);
//        new RxPermissions(mContext).request()
        RxPermissionUtil.with(this)
            .setSecondTip("二次")
            .setForeverTip("已经禁止了，去设置")
            .setShowSecondTip(true)
            .setShowForeverTip(true)
            .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
            .requestPermission(new RxPermissionUtil.OnPermissionCallback() {
                @Override
                public void permissionGranted() {
                    LogUtil.i(TAG, "权限来了");
                    mPresenter.onPermissionGanted();
                }

                @Override
                public void permissionDenied() {
                    LogUtil.i(TAG, "权限禁了");
                }
            });
    }
}
