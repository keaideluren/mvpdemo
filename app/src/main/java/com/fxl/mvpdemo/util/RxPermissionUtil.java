package com.fxl.mvpdemo.util;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.fxl.mvpdemo.R;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator 可爱的路人 on 2017/2/28.
 * RxPermission的工具封装，没有权限的提示
 */

public class RxPermissionUtil {
    private CharSequence secondTip;
    private CharSequence foreverTip;
    private String[] permissions;
    private FragmentActivity context;
    private boolean isShowSecondTip = true;
    private boolean isShowForeverTip = true;

    private static final byte[] lock = new byte[0];
    private AlertDialog.Builder tipDialogBuilder;
    private AlertDialog tipDialog;

    public RxPermissionUtil(@NonNull FragmentActivity context) {
        this.context = context;
    }

    public RxPermissionUtil setSecondTip(CharSequence secondTip) {
        this.secondTip = secondTip;
        return this;
    }

    public RxPermissionUtil setForeverTip(CharSequence foreverTip) {
        this.foreverTip = foreverTip;
        return this;
    }

    public RxPermissionUtil setShowSecondTip(boolean showSecondTip) {
        isShowSecondTip = showSecondTip;
        return this;
    }

    public RxPermissionUtil setShowForeverTip(boolean showForeverTip) {
        isShowForeverTip = showForeverTip;
        return this;
    }

    public RxPermissionUtil permissions(String... permissions) {
        this.permissions = permissions;
        return this;
    }

    public void requestPermission(@NonNull final OnPermissionCallback permissionGranted) {
        if (context == null) {
            throw new NullPointerException("请求权限上下文不能为空");
        }
        if (permissions == null || permissions.length == 0) {
            throw new NullPointerException("请求的权限不能为空");
        }
        if (!afterM()) {
            permissionGranted.permissionGranted();
            return;
        }
        new RxPermissions(context)
                .requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(final Permission permission) throws Exception {
                        if (permission.granted) {
                            String[] permissionNew = new String[permissions.length - 1];
                            int dd1 = 0;
                            for (int i = 0; i < permissions.length; i++) {
                                if (!permissions[i].equals(permission.name)) {
                                    permissionNew[i - dd1] = permissions[i];
                                } else {
                                    dd1++;
                                }
                            }
                            permissions = permissionNew;
                            if (permissions.length == 0) {
                                permissionGranted.permissionGranted();
                            }
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            //第二次出现
                            if (isShowSecondTip) {
                                showSecondTipDialog(context, permissionGranted, permission);
                            } else {
                                permissionGranted.permissionDenied();
                            }
                        } else {
                            //没有给权限，直接禁了，这里应该跳到设置界面
                            if (isShowForeverTip) {
                                showForeverTipDialog(context, permissionGranted);
                            } else {
                                permissionGranted.permissionDenied();
                            }
                        }
                    }
                });
    }

    private void showSecondTipDialog(final FragmentActivity context
            , final OnPermissionCallback permissionGranted, final Permission permission) {
        if (tipDialogBuilder == null) {
            tipDialogBuilder = new AlertDialog.Builder(context)
                    .setTitle("请求权限")
                    .setCancelable(false)

                    .setNegativeButton("拒绝", (dialog, which) -> {
                        dialog.dismiss();
                        permissionGranted.permissionDenied();
                    });
        }
        if (tipDialog == null || !tipDialog.isShowing()) {
            tipDialog = tipDialogBuilder
                    .setMessage(TextUtils.isEmpty(secondTip) ? context.getString(R.string.no_permission_tip) : secondTip)
                    .setPositiveButton("确定", (dialog, which) -> {
                        dialog.dismiss();
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            requestPermission(permissionGranted);
                        }
                    })
                    .create();
            tipDialog.setCanceledOnTouchOutside(false);
            tipDialog.show();
        }
    }

    private void showForeverTipDialog(final FragmentActivity context, final OnPermissionCallback permissionGranted) {
        if (tipDialogBuilder == null) {
            tipDialogBuilder = new AlertDialog.Builder(context)
                    .setTitle("请求权限")
                    .setCancelable(false)
                    .setNegativeButton("拒绝", (dialog, which) -> {
                        dialog.dismiss();
                        permissionGranted.permissionDenied();
                    });
        }
        if (tipDialog == null || !tipDialog.isShowing()) {
            tipDialog = tipDialogBuilder
                    .setMessage(TextUtils.isEmpty(foreverTip) ? context.getString(R.string.no_permission_tip) : foreverTip)
                    .setPositiveButton("确定", (dialog, which) -> {
                        dialog.dismiss();

                        Uri packageURI = Uri.parse("package:" + context.getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        context.startActivity(intent);
                    })
                    .create();
            tipDialog.setCanceledOnTouchOutside(false);
            tipDialog.show();
        }
    }


    public interface OnPermissionCallback {
        void permissionGranted();

        void permissionDenied();
    }

    private boolean afterM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
