package com.fxl.mvpdemo.mylibrary.PTRUi;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fxl.mvpdemo.mylibrary.R;
import com.fxl.mvpdemo.mylibrary.utils.ScreenUtils;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;


/**
 * Created by Administrator 可爱的路人 on 2017/5/8.
 * 下拉刷新的那个动画
 */

public class CustomHeadView extends FrameLayout implements PtrUIHandler {

    private AnimationDrawable background;

    public CustomHeadView(@NonNull Context context) {

        super(context);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ScreenUtils.dp2px(getContext(), 60), Gravity.CENTER);
        setLayoutParams(layoutParams);
        FrameLayout inflate = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.refresh_view, null, false);
        ImageView ivRefresh =(ImageView) inflate.findViewById(R.id.iv_refresh_anim);
        background = (AnimationDrawable) ivRefresh.getBackground();
        inflate.removeAllViews();
        addView(ivRefresh);

    }

    public CustomHeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
    }


    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        background.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        background.stop();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
