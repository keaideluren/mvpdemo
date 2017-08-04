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

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.fxl.mvpdemo.R;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator 可爱的路人 on 2017/2/28.
 * RxPermission的工具封装，没有权限的提示
 */

public class RxPermissionUtil {
    private int totalGranted = 0;
    private CharSequence secondTip;
    private CharSequence foreverTip;
    private String[] permissions;
    private FragmentActivity context;
    private boolean isShowSecondTip = true;
    private boolean isShowForeverTip = true;

    private static RxPermissionUtil instance;
    private static final byte[] lock = new byte[0];
    private AlertDialog tipDialog;

    private RxPermissionUtil() {
    }

    public static RxPermissionUtil with(@NonNull FragmentActivity context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new RxPermissionUtil();
                }
            }
        }
        instance.totalGranted = 0;
        instance.context = context;
        instance.permissions = null;
        instance.secondTip = null;
        instance.foreverTip = null;
        return instance;
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
                            totalGranted++;
                            if (totalGranted == permissions.length) {
                                permissionGranted.permissionGranted();
                            }
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            //第二次出现
                            if (isShowSecondTip) {
                                getSecondTipDialog(context, permissionGranted, permission).show();
                            } else {
                                permissionGranted.permissionDenied();
                            }
                        } else {
                            //没有给权限，直接禁了，这里应该跳到设置界面
                            if (isShowForeverTip) {
                                getForeverTipDialog(context, permissionGranted).show();
                            } else {
                                permissionGranted.permissionDenied();
                            }
                        }
                    }
                });
    }

    private AlertDialog getSecondTipDialog(final FragmentActivity context
            , final OnPermissionCallback permissionGranted, final Permission permission) {
        if (tipDialog == null) {
            tipDialog = new AlertDialog.Builder(context)
                    .setTitle("请求权限")
                    .setMessage(TextUtils.isEmpty(secondTip) ? context.getString(R.string.no_permission_tip) : secondTip)
                    .setCancelable(false)
                    .setPositiveButton("确定", (dialog, which) -> {
                        dialog.dismiss();
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            requestPermission(permissionGranted);
                        }
                    })
                    .setNegativeButton("拒绝", (dialog, which) -> {
                        dialog.dismiss();
                        if (isShowForeverTip) {
                            getForeverTipDialog(context, permissionGranted);
                        } else {
                            permissionGranted.permissionDenied();
                        }
                    })
                    .create();
            tipDialog.setCanceledOnTouchOutside(false);
        }
        return tipDialog;
    }

    private AlertDialog getForeverTipDialog(final FragmentActivity context, final OnPermissionCallback permissionGranted) {
        if (tipDialog == null) {
            tipDialog = new AlertDialog.Builder(context)
                    .setTitle("请求权限")
                    .setMessage(TextUtils.isEmpty(foreverTip) ? context.getString(R.string.no_permission_tip) : foreverTip)
                    .setCancelable(false)
                    .setPositiveButton("确定", (dialog, which) -> {
                        dialog.dismiss();

                        Uri packageURI = Uri.parse("package:" + context.getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        context.startActivity(intent);
                    })
                    .setNegativeButton("拒绝", (dialog, which) -> {
                        dialog.dismiss();
                        permissionGranted.permissionDenied();
                    })
                    .create();
            tipDialog.setCanceledOnTouchOutside(false);
        }
        return tipDialog;
    }


    public interface OnPermissionCallback {
        void permissionGranted();

        void permissionDenied();
    }

    private boolean afterM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
