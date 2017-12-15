/*
 * Copyright © Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fxl.mvpdemo.pages.qrcodescanner;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.mvp.base.SimpleActivity;
import com.fxl.mvpdemo.util.RxPermissionUtil;
import com.yanzhenjie.camera.CameraPreview;
import com.yanzhenjie.camera.ScanCallback;

import butterknife.BindView;

/**
 * Created by 可爱的路人 on 2017/5/5.
 */
public class QrcodeScannerActivity extends SimpleActivity {
    @BindView(R.id.capture_crop_view)
    RelativeLayout mScanCropView;
    @BindView(R.id.capture_scan_line)
    ImageView mScanLine;
    @BindView(R.id.capture_preview)
    CameraPreview mPreviewView;

    private ValueAnimator mScanAnimator;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);
    }


    @Override
    protected void initEventAndData() {
        mPreviewView.setScanCallback(resultCallback);
    }

    /**
     * Accept scan result.
     */
    private ScanCallback resultCallback = result -> {
        //todo QrcodeScannerPresenter 进行处理，结果上传等 ，这里仅Toast展示一下，就不需要MVP了，因为没有数据
        stopScan();
        Toast.makeText(QrcodeScannerActivity.this, result, Toast.LENGTH_LONG).show();
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (mScanAnimator != null) {
            startScanUnKnowPermission();
        }
    }

    @Override
    public void onPause() {
        // Must be called here, otherwise the camera should not be released properly.
        stopScan();
        super.onPause();
    }

    /**
     * Do not have permission to request for permission and start scanning.
     */
    private void startScanUnKnowPermission() {
        new RxPermissionUtil(this)
            .permissions(Manifest.permission.CAMERA)
            .setForeverTip("扫描二维码需要相机权限")
            .setSecondTip("扫描二维码一定需要相机权限")
            .setShowForeverTip(false)
            .setShowSecondTip(true)
            .requestPermission(new RxPermissionUtil.OnPermissionCallback() {
                @Override
                public void permissionGranted() {
                    startScanWithPermission();
                }

                @Override
                public void permissionDenied() {
                    finish();
                }
            });
    }

    /**
     * There is a camera when the direct scan.
     */
    private void startScanWithPermission() {
        if (mPreviewView.start()) {
            mScanAnimator.start();
        } else {
            new AlertDialog.Builder(this)
                .setTitle("启动相机失败")
                .setMessage("检查是否被其它程序占用")
                .setCancelable(false)
                .setPositiveButton("确定", (dialog, which) -> finish())
                .show();
        }
    }

    /**
     * Stop scan.
     */
    private void stopScan() {
        mScanAnimator.cancel();
        mPreviewView.stop();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mScanAnimator == null) {
            int height = mScanCropView.getMeasuredHeight() - 25;
            mScanAnimator = ObjectAnimator.ofFloat(mScanLine, "translationY", 0F, height).setDuration(3000);
            mScanAnimator.setInterpolator(new LinearInterpolator());
            mScanAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mScanAnimator.setRepeatMode(ValueAnimator.REVERSE);

            startScanUnKnowPermission();
        }
    }
}

